package com.sksamuel.scoverage.samples

import java.util.UUID

import org.scalatest.{FlatSpec, OneInstancePerTest}
import akka.actor.{ActorSystem, Props}
import akka.testkit.TestProbe

import scala.concurrent.duration._
import scala.language.postfixOps
import scala.util.Random

/** @author Stephen Samuel */
class ClientActorTest extends FlatSpec with OneInstancePerTest {

  val req =
    MarketOrderRequest(Instrument("CVX", "Chevron"), BigDecimal.valueOf(400))

  val system = ActorSystem("scales-test")
  val priceEngine = TestProbe()(system)
  val orderEngine = TestProbe()(system)
  val client = system.actorOf(
    Props(classOf[ClientActor], priceEngine.ref, orderEngine.ref)
  )

  "a client actor" should "ask for a quote" in {
    priceEngine.expectMsgType[RequestForQuote]
  }

  it should "send a market order request if ask under minimum" in {
    val quote = SpotQuote(
      Instrument("CVX", "Chevron"),
      Currency("USD"),
      BigDecimal.valueOf(49.99),
      BigDecimal.valueOf(49.99)
    )
    client ! quote
    orderEngine.expectMsgType[MarketOrderRequest]
  }

  it should "not send an order request if ask is over minimum" in {
    val quote = SpotQuote(
      Instrument("CVX", "Chevron"),
      Currency("USD"),
      BigDecimal.valueOf(50.01),
      BigDecimal.valueOf(50.01)
    )
    client ! quote
    orderEngine.expectNoMsg(2 seconds)
  }

  it should "MarketOrderReject" in {
    val quote = MarketOrderReject(
      MarketOrderRequest(
        instrument = Instrument("symbol", "name"),
        units = BigDecimal(Random.nextInt(100))
      )
    )
    client ! quote
  }

  it should "MarketOrderAccept" in {
    val quote = MarketOrderAccept(
      Order(
        MarketOrderRequest(
          instrument = Instrument("symbol", "name"),
          units = BigDecimal(Random.nextInt(100))
        ),
        BigDecimal(Random.nextInt(100))
      ),
      MarketOrderRequest(
        instrument = Instrument("symbol", "name"),
        units = BigDecimal(Random.nextInt(100))
      )
    )
    client ! quote
  }

}
