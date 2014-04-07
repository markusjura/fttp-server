package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._
import com.wordnik.swagger.annotations._
import javax.ws.rs.PathParam
import ApiDocumentation._

@Api(value = "/logs", description = "Log operations")
object LogController extends Controller {

  // Stores the clients
  var clientUrls: Seq[String] = Seq.empty

  val jsonSuccess: JsValue = Json.obj("success" -> true)

  @ApiOperation(
    value = "Subscribes a log client to the server.", nickname = "subscribe", httpMethod = "POST")
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "body", value = "URL to the receive HTTP Endpoint as json.", required = true, dataType = "application/json",
      paramType = "body", defaultValue = logsDefaultValue)))
  @ApiResponses(Array(
    new ApiResponse(code = 200, message = "Log client subscription was successful."),
    new ApiResponse(code = 400, message = "Body is invalid. Need to contains an url as json.")))
  def subscribe = Action(parse.json) { request =>
    (request.body \ "url").asOpt[String] match {
      case None => BadRequest (Json.obj ("error" -> "Body is invalid. Need to contains an url as json."))
      case Some(clientUrl) =>
        Logger.debug (s"Client : $clientUrl subscribed")
        clientUrls = clientUrl +: clientUrls
        Ok (jsonSuccess)
    }
  }

  @ApiOperation(
    value = "Unsubscribes a log client from the server.", nickname = "unsubscribe", httpMethod = "POST")
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "body", value = "URL to the receive HTTP Endpoint as json.", required = true, dataType = "application/json",
      paramType = "body", defaultValue = logsDefaultValue)))
  @ApiResponses(Array(
    new ApiResponse(code = 200, message = "Log client has been successfully unsubscribed."),
    new ApiResponse(code = 400, message = "Body is invalid. Need to contains an url as json.")))
  def unsubscribe = Action(parse.json) { request =>
    (request.body \ "url").asOpt[String] match {
      case None => BadRequest(Json.obj("error" -> "Body is invalid. Need to contains an url as json."))
      case Some(clientUrl) =>
        Logger.debug(s"Client : $clientUrl unsubscribed")
        clientUrls = clientUrls.filterNot(_ == clientUrl)
        Ok(jsonSuccess)
    }
  }
}