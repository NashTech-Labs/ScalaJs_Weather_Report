package integrations

import java.util.concurrent.TimeUnit

import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.{ FirefoxDriver, FirefoxProfile }
import play.api.test.{ PlaySpecification, _ }

class IntegrationTest extends PlaySpecification {

  def firefoxWebDriver: WebDriver = {
    val firefoxProfile = new FirefoxProfile()
    firefoxProfile.setPreference("startup.homepage_welcome_url.additional", "")
    new FirefoxDriver(firefoxProfile)
  }

  val time = 10

  "User" should {
    "be able to go to dashboard page" in new WithBrowser(firefoxWebDriver) {
      browser.goTo("/")
      browser.manage.timeouts().implicitlyWait(time, TimeUnit.SECONDS)
      browser.pageSource must contain("Dashboard")
    }

    "be able to get the correct project name" in new WithBrowser(firefoxWebDriver) {
      browser.goTo("/")
      browser.manage.timeouts().implicitlyWait(time, TimeUnit.SECONDS)
      browser.pageSource must contain("CodeSquadTest")
    }

    "be able to get the number of warnings" in new WithBrowser(firefoxWebDriver) {
      browser.goTo("/")
      browser.manage.timeouts().implicitlyWait(time, TimeUnit.SECONDS)
      browser.pageSource must contain("0")
    }

    "get the correct project code analysis for warns" in new WithBrowser(firefoxWebDriver) {
      browser.goTo("/")
      browser.manage.timeouts().implicitlyWait(time, TimeUnit.SECONDS)
      browser.pageSource must contain("9")
    }

    "get the correct project code analysis for errors" in new WithBrowser(firefoxWebDriver) {
      browser.goTo("/")
      browser.manage.timeouts().implicitlyWait(time, TimeUnit.SECONDS)
      browser.pageSource must contain("6")
    }

    "get the correct project code analysis for infos" in new WithBrowser(firefoxWebDriver) {
      browser.goTo("/")
      browser.manage.timeouts().implicitlyWait(time, TimeUnit.SECONDS)
      browser.pageSource must contain("71")
    }

    "get the correct test coverage" in new WithBrowser(firefoxWebDriver) {
      browser.goTo("/")
      browser.manage.timeouts().implicitlyWait(time, TimeUnit.SECONDS)
      browser.pageSource must contain("81.95")
    }

    "get the correct copy paste detector report" in new WithBrowser(firefoxWebDriver) {
      browser.goTo("/")
      browser.manage.timeouts().implicitlyWait(time, TimeUnit.SECONDS)
      browser.pageSource must contain("17")
    }

    "get the danger image on missing dependencies" in new WithBrowser(firefoxWebDriver) {
      browser.goTo("/")
      browser.manage.timeouts().implicitlyWait(time, TimeUnit.SECONDS)
      browser.pageSource must contain("img")
    }

  }

}

