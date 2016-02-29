package com.knoldus.weather

import org.scalajs.dom.raw.XMLHttpRequest
import utest._

import org.scalajs.jquery.jQuery

import scala.scalajs.js
import scala.scalajs.js.JSON

object WeatherTest extends TestSuite {

  val weather = new WeatherReport {
    override def getWeatherReport(name: js.Dynamic) = {
      val response =
        if(name.toString == "Delhi") {
          """{"coord":{"lon":77.22,"lat":28.67},"weather":[{"id":701,"main":"Mist","description":"mist","icon":"50n"}],"base":"stations","main":{"temp":292.15,"pressure":1019,"humidity":82,"temp_min":292.15,"temp_max":292.15},"visibility":1000,"wind":{"speed":1.5,"deg":110},"clouds":{"all":32},"dt":1456707600,"sys":{"type":1,"id":7809,"message":0.0103,"country":"IN","sunrise":1456708622,"sunset":1456750231},"id":1273294,"name":"Delhi","cod":200}"""
        }else {"""{"coord":{"lon":-74.01,"lat":40.71},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],"base":"stations","main":{"temp":279.2,"pressure":1007,"humidity":61,"temp_min":274.15,"temp_max":284.15},"visibility":16093,"wind":{"speed":3.1,"deg":200},"clouds":{"all":1},"dt":1456727956,"sys":{"type":1,"id":1975,"message":0.0215,"country":"US","sunrise":1456745419,"sunset":1456786024},"id":5128581,"name":"New York","cod":200}"""}

      JSON.parse(response)
    }

    override def initialize(lat: Double, long: Double) = ()
  }
  // Setup UI
  weather.addUIElement()

  def tests = TestSuite {
    "Page should contain search text box with button." - {
      assert(jQuery("#name").value() == "Delhi")
      val button = jQuery("#submit")
      assert(button.length == 1)
    }
    "Page should display weather report for default city." - {
      val button = jQuery("#submit")
      assert(jQuery("#cityName:contains('Delhi,IN')").length == 0)
      button.click()
      assert(jQuery("#cityName:contains('Delhi,IN')").length == 1)
      assert(jQuery("#geocoords:contains('[77.22, 28.67]')").length == 1)
    }
    "Page should clean before search weather report for a new city." - {
      jQuery("#name").value("Newyork")
      val button = jQuery("#submit")
      assert(jQuery("#cityName:contains('New York,US')").length ==0)
      button.click()
      assert(jQuery("#cityName:contains('Delhi,IN')").length ==0)
      assert(jQuery("#cityName:contains('New York,US')").length ==1)
      assert(jQuery("#geocoords").value() != "[-74.01, 40.71]")
    }
  }
}

