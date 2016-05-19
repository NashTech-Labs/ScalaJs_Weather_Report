
package controllers

import org.specs2.mutable._
import play.api.test.Helpers._
import play.api.test._

class WeatherControllerTest extends Specification {

  "Application" should {

    "send 404 on a bad request" in new WithApplication {
      route(FakeRequest(GET, "/boum")) must beNone
    }
  }
}

