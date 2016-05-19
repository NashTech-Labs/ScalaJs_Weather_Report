package com.knoldus.weather

import org.scalajs.jquery.jQuery
import utest._

import scala.scalajs.js
import scala.scalajs.js.JSON

object WeatherTest extends TestSuite {

  // Setup UI

  val weather = new WeatherReport {
    override def initialize(lat: Double, long: Double) = ()
  }

  // Setup UI
  weather.showReport()

  def tests = TestSuite {
    "Page should contain search text box with button." - {
      assert(jQuery("#name").value() == "Delhi")
      val button = jQuery("#submit")
      assert(button.length == 1)
    }
  }
}

