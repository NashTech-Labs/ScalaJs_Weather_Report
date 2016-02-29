package com.knoldus.weather

import scala.scalajs.js.annotation.JSExport
import scala.scalajs.js
import js.Dynamic.{global => g, newInstance => jsnew, literal => lit}
import org.scalajs.dom
import dom.document
import org.scalajs.dom.XMLHttpRequest
import scala.scalajs.js.{Array, JSON, Date}
import org.scalajs.jquery.{JQuery, jQuery}

trait DataGenerator {
  def getWeatherReport(name: js.Dynamic) = {
    val xmlHttpRequest = new XMLHttpRequest
    xmlHttpRequest.open("GET", "http://api.openweathermap.org/data/2.5/weather?q=" + name + "&appid=44db6a862fba0b067b1930da0d769e98", false)
    xmlHttpRequest.send(null);
    println("xmlHttpRequest.responseText.........",xmlHttpRequest.responseText)
    JSON.parse(xmlHttpRequest.responseText)
  }
}

object Weather extends js.JSApp with DataGenerator{

  def main(): Unit = {
    jQuery(addUIElement _)
  }

  def addUIElement() = {
    jQuery("body").append(
      """<div class="col-md-12"
     style="border-bottom: 1px solid #eee; background: #3D4048">
    <h1 style="margin-bottom: 5px; color: #DCD0C0; text-align: center;">
        <img src="./images/image.png" height="60px" width="60px"> <span
            style="margin-left: 20px; text-transform: uppercase; text-shadow: 2px 2px 4px #000;">Weather
				Report -</span> <span style="text-shadow: 1px 1px 1px #000;">Get the
				mood of your city on one click</span> <img src="./images/image.png"
                                                           height="60px" width="60px" style="margin-left: 20px;">
    </h1>
</div>
<div class="col-md-12" id="search" style="margin-top: 10%;">
</div><div class="col-md-12 maincontainer" id="tempDetail"
     style="margin-top: 30px; border-top: 2px solid #ccc; padding-top: 30px; border-bottom: 2px solid #ccc; padding-bottom: 30px; display: none;">
    <div>
        <div class="col-md-6">
            <div style="width: 530px; height: 400px; margin-left: 60px;">

                <div id="cityName"
                     style="font-size: 28px; color: #67890a; font-weight: bold;"></div>


                <table class="table-bordered table-striped"
                       style="width: 540px; text-align: center; margin-top: 10px">
                    <tr>
                        <td style="padding: 0px; font-weight: bold; font-size: 22px;">
                            <div
                                    id="temp"></div>
                        </td>
                        <td style="padding: 0px; font-weight: bold;">
                            <div
                                    id="weather"></div>
                        </td>
                    </tr>
                    <tr>
                        <td>Pressure</td>
                        <td id="pressure"></td>
                    </tr>
                    <tr>
                        <td>Humidity</td>
                        <td id="humidity"></td>
                    </tr>
                    <tr>
                        <td>Sunrise</td>
                        <td id="sunrise"></td>
                    </tr>
                    <tr>
                        <td>Sunset</td>
                        <td id="sunset"></td>
                    </tr>
                    <tr>
                        <td>Geo coords</td>
                        <td id="geocoords"></td>
                    </tr>

                </table>
            </div>
        </div>

        <!-- Placeholder for the Google Map -->
        <div class="col-md-6">
            <div id="map_canvas" style="height: 430px; width: 512px;"></div>
        </div>
    </div>
</div>""")
    jQuery("#search").append(
      """<input name="name" id="name" size="15" type="text"
			placeholder="Enter a city"
			style="width: 60%; height: 35px; margin: 0% 0px 0px 16%; border-radius: 0px; box-shadow: none; padding-left: 5px" value="Delhi"/>""")
    jQuery(
      """<button type="button" name="submit" id="submit" class="btn btn-info"
			style="height: 35px; margin: -1px 0px 0px 0%; border-radius: 0px;">Search</button>""")
      .click(showDetail _)
      .appendTo(jQuery("#search"))
  }

  def showDetail() {
    cleanUI

    val name = jQuery("#name").value()
    val result = getWeatherReport(name)
    if (result.cod.toString() == "404") {
      g.alert("Please Enter A Valid City Name.")
    } else {
      populateWeatherReprt(result)
    }
  }

  private def cleanUI: JQuery = {
    jQuery("#cityName").empty()
    jQuery("#weather").empty()
    jQuery("#pressure").empty()
    jQuery("#humidity").empty()
    jQuery("#sunrise").empty()
    jQuery("#sunset").empty()
    jQuery("#geocoords").empty()
    jQuery("#temp").empty()
  }

  private def populateWeatherReprt(result: js.Dynamic) = {
    val weather = result.weather.asInstanceOf[Array[js.Dynamic]](0)
    jQuery("#tempDetail").attr("style", "display:block;")
    jQuery("#cityName").append(result.name + "," + result.sys.country)
    val image = "http://openweathermap.org/img/w/" + weather.icon + ".png"
    jQuery("#temp").append("<img src=" + image + " >" + Math.floor(result.main.temp.toString.toDouble - 273.15))
    jQuery("#weather").append("" + weather.main)
    jQuery("#pressure").append("" + result.main.pressure + " hpa")
    jQuery("#humidity").append(result.main.humidity + " %")
    jQuery("#sunrise").append(msToTime(result.sys.sunrise.toString.toLong))
    jQuery("#sunset").append(msToTime(result.sys.sunset.toString.toLong))
    jQuery("#geocoords").append("[" + result.coord.lon + ", " + result.coord.lat + "]")
    initialize(result.coord.lat.toString.toDouble, result.coord.lon.toString.toDouble)
  }


  def initialize(lat: Double, long: Double) = {
    val map_canvas = document.getElementById("map_canvas")
    val map_options = lit(center = (jsnew(g.google.maps.LatLng)) (lat, long), zoom = 3, mapTypeId = g.google.maps.MapTypeId.ROADMAP)
    val gogleMap = (jsnew(g.google.maps.Map)) (map_canvas, map_options)
    val marker = (jsnew(g.google.maps.Marker)) (lit(map = gogleMap, position = (jsnew(g.google.maps.LatLng)(lat, long))))
  }

  def msToTime(unix_timestamp: Long): String = {
    val date = new Date(unix_timestamp * 1000);
    val hrs = date.getHours();
    val mins = date.getMinutes();
    val secs = date.getSeconds();
    hrs + ":" + mins + ":" + secs
  }

}
