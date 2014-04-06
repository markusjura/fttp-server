package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._

object LogController extends Controller {

  var clientUrls: Seq[String] = Seq.empty

  def subscribe = Action(parse.json) { request =>
    val clientUrl = (request.body \ "url").as[String]
    Logger.debug(s"Client : $clientUrl subscribed")
    clientUrls = clientUrl +: clientUrls
    Ok(Json.obj("success" -> true))
  }

  def unsubscribe = Action(parse.json) { request =>
    val clientUrl = (request.body \ "url").as[String]
    Logger.debug(s"Client : $clientUrl unsubscribed")
    clientUrls = clientUrls.filterNot(_ == clientUrl)
    Ok(Json.obj("success" -> true))
  }
}