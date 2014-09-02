package com.knoldus.weather

import scala.scalajs.js.annotation.JSExport
import scala.scalajs.js
import js.Dynamic.{ global => g, newInstance => jsnew, literal => lit }
import org.scalajs.dom
import dom.document
import org.scalajs.dom.XMLHttpRequest
import scala.scalajs.js.JSON
import scala.scalajs.js.Date

object Weather extends js.JSApp {

  def main(): Unit = {
    appendInputBox
    appendSubmitButtom
    appendDiv
  }

  def appendInputBox: Unit = {
    val input = document.createElement("input")
    input.setAttribute("name", "name")
    input.setAttribute("id", "name")
    input.setAttribute("type", "text")
    input.setAttribute("size", "15")
    document.body.appendChild(input)
  }

  def appendSubmitButtom: Unit = {
    val input = document.createElement("input")
    input.setAttribute("name", "submit")
    input.setAttribute("id", "submit")
    input.setAttribute("value", "submit")
    input.setAttribute("type", "button")
    input.setAttribute("size", "15")
    input.setAttribute("onclick", "com.knoldus.weather.Weather().showDetail()")
    document.body.appendChild(input)
  }

  def appendDiv: Unit = {
    val div = document.createElement("div")
    div.setAttribute("id", "tempDetail")
    div.setAttribute("style", "display: none")

    val innerdiv1 = document.createElement("div")
    innerdiv1.setAttribute("id", "cityName")

    val innerdiv2 = document.createElement("div")
    innerdiv2.setAttribute("id", "temp")

    val innerdiv3 = document.createElement("div")
    innerdiv3.setAttribute("id", "weather")

    val innertable = document.createElement("table")
    innertable.setAttribute("style", "width: 300px")

    val innerRow1 = document.createElement("tr")
    innerRow1.setAttribute("style", "width: 300px")

    val innertd1 = document.createElement("td")
    val textNode1 = document.createTextNode("Pressure")
    innertd1.appendChild(textNode1)
    innerRow1.appendChild(innertd1)

    val innertd2 = document.createElement("td")
    val textNode2 = document.createTextNode("Humidity")
    innertd2.appendChild(textNode2)
    innerRow1.appendChild(innertd2)

    val innertd3 = document.createElement("td")
    val textNode3 = document.createTextNode("Sunrise")
    innertd3.appendChild(textNode3)
    innerRow1.appendChild(innertd3)

    val innertd4 = document.createElement("td")
    val textNode4 = document.createTextNode("Sunset")
    innertd4.appendChild(textNode4)
    innerRow1.appendChild(innertd4)

    val innertd5 = document.createElement("td")
    val textNode5 = document.createTextNode("Geo coords")
    innertd5.appendChild(textNode5)
    innerRow1.appendChild(innertd5)

    innertable.appendChild(innerRow1)

    val innerRow2 = document.createElement("tr")
    innerRow2.setAttribute("style", "width: 300px")

    val innertd_1 = document.createElement("td")
    innertd_1.setAttribute("id", "pressure")
    val innertd_2 = document.createElement("td")
    innertd_2.setAttribute("id", "humidity")
    val innertd_3 = document.createElement("td")
    innertd_3.setAttribute("id", "sunrise")
    val innertd_4 = document.createElement("td")
    innertd_4.setAttribute("id", "sunset")
    val innertd_5 = document.createElement("td")
    innertd_5.setAttribute("id", "geocoords")

    innerRow2.appendChild(innertd_1)
    innerRow2.appendChild(innertd_2)
    innerRow2.appendChild(innertd_3)
    innerRow2.appendChild(innertd_4)
    innerRow2.appendChild(innertd_5)

    innertable.appendChild(innerRow2)

    val innerdiv4 = document.createElement("div")
    innerdiv4.setAttribute("id", "map_canvas")

    div.appendChild(innerdiv1)
    div.appendChild(innerdiv2)
    div.appendChild(innerdiv3)
    div.appendChild(innertable)
    div.appendChild(innerdiv4)

    document.body.appendChild(div)
  }

  @JSExport
  def showDetail() {

    val name = g.document.getElementById("name").value
    val xmlHttpRequest = new dom.XMLHttpRequest

    xmlHttpRequest.open("GET", "http://api.openweathermap.org/data/2.5/weather?q=" + name, false)
    xmlHttpRequest.send(null);
    val result = JSON.parse(xmlHttpRequest.responseText)

    if (result.cod.toString() == "404") {
      g.alert("Please Enter A Valid City Name.")
    } else {
      val weather = result.weather.asInstanceOf[js.Array[js.Dynamic]](0)
      document.getElementById("tempDetail").style.display = "block"
      document.getElementById("cityName").innerHTML = result.name + "," + result.sys.country
      val image = "http://openweathermap.org/img/w/" + weather.icon + ".png"
      document.getElementById("temp").innerHTML = "<img src=" + image + " >" + (result.main.temp - 273.15)
      document.getElementById("weather").innerHTML = "" + weather.main
      document.getElementById("pressure").innerHTML = "" + result.main.pressure + " hpa"
      document.getElementById("humidity").innerHTML = result.main.humidity + " %"
      document.getElementById("sunrise").innerHTML = msToTime(result.sys.sunrise.toString.toLong)
      document.getElementById("sunset").innerHTML = msToTime(result.sys.sunset.toString.toLong)
      document.getElementById("geocoords").innerHTML = "[" + result.coord.lon + ", " + result.coord.lat + "]"
      initialize(result.coord.lat.toString.toDouble, result.coord.lon.toString.toDouble)
    }
  }

  def initialize(lat: Double, long: Double) = {
    val map_canvas = document.getElementById("map_canvas")
    val map_options = lit(center = (jsnew(g.google.maps.LatLng))(lat, long), zoom = 3, mapTypeId = g.google.maps.MapTypeId.ROADMAP)
    val gogleMap = (jsnew(g.google.maps.Map))(map_canvas, map_options)
    val marker = (jsnew(g.google.maps.Marker))(lit(map = gogleMap, position = (jsnew(g.google.maps.LatLng)(lat, long))))
  }

  def msToTime(unix_timestamp: Long) = {
    val date = new Date(unix_timestamp * 1000);
    val hrs = date.getHours();
    val mins = date.getMinutes();
    val secs = date.getSeconds();
    hrs + ":" + mins + ":" + secs
  }

}