
package controllers

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.test.{ FakeRequest, WithApplication }

import play.api.test.Helpers._

@RunWith(classOf[JUnitRunner])
class DashboardControllerTest extends Specification {

  val dashboardController = new WeatherController

  "Dashboard" should {

    "show the dashboard" in new WithApplication {

      val result = route(FakeRequest(GET, "/")).get
      status(result) must equalTo(OK)
      contentType(result) must beSome.which(_ == "text/html")
    }

    "show the dashboard content" in new WithApplication {

      val result = route(FakeRequest(GET, "/")).get
      contentAsString(result) must contain("Dashboard")
    }

    "show the project name" in new WithApplication {

      val result = route(FakeRequest(GET, "/")).get
      contentAsString(result) must contain("CodeSquadTest")
    }

    "show the scalastyle warnings" in new WithApplication {

      val result = route(FakeRequest(GET, "/")).get
      contentAsString(result) must contain("0")
    }

    "show the scapegoat warnings" in new WithApplication {

      val result = route(FakeRequest(GET, "/")).get
      contentAsString(result) must contain("9")
    }

    "show the scapegoat error" in new WithApplication {

      val result = route(FakeRequest(GET, "/")).get
      contentAsString(result) must contain("6")
    }

    "show the scapegoat infos" in new WithApplication {

      val result = route(FakeRequest(GET, "/")).get
      contentAsString(result) must contain("71")
    }

    "show the scoverage report" in new WithApplication {

      val result = route(FakeRequest(GET, "/")).get
      contentAsString(result) must contain("81.95")
    }

    "show the copy paste detector report" in new WithApplication {

      val result = route(FakeRequest(GET, "/")).get
      contentAsString(result) must contain("17")
    }
  }
}

