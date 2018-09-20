scoverage-samples
==================
[![CircleCI](https://circleci.com/gh/choisangmin/sbt-scoverage-samples.svg?style=svg)](https://circleci.com/gh/choisangmin/sbt-scoverage-samples)
[![codecov](https://codecov.io/gh/choisangmin/sbt-scoverage-samples/branch/master/graph/badge.svg)](https://codecov.io/gh/choisangmin/sbt-scoverage-samples)

A sample program for demonstrating scoverage's code coverage. This sample is an akka based mock quote engine. Note: This project deliberately doesn't have 100% coverage for demonstration purposes.

It uses:

* Akka 2.4.16
* Scalatest for unit tests
* Scoverage 1.5.0

To run:

```
sbt clean coverage test coverageReport
```

## License
```
This software is licensed under the Apache 2 license, quoted below.

Copyright 2014 Stephen Samuel

Licensed under the Apache License, Version 2.0 (the "License"); you may not
use this file except in compliance with the License. You may obtain a copy of
the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
License for the specific language governing permissions and limitations under
the License.
```
