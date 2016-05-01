package controllers

import play.api.mvc._

import scalaj.http.Http
import com.typesafe.config.ConfigFactory

class WeatherController extends Controller {

  /**
   * Display Weather Information
   */
  def index: Action[AnyContent] = Action {
    Ok(views.html.index())
  }

  /**
   * This action is used to handle Ajax request
   *
   * @return
   */
  def ajaxCall(city: String) = Action { implicit request =>
    val key = ConfigFactory.load().getString("appId")
    val response = Http("http://api.openweathermap.org/data/2.5/weather").params(Map("q" -> city, "appid" -> key)).asString
    Ok(response.body)
  }
}
