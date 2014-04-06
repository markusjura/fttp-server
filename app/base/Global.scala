package base

import play.api._
import controllers.LogController
import models.Log
import play.api.libs.concurrent.Akka
import play.api.libs.json.Json
import play.api.libs.ws.WS
import scala.concurrent.duration._
import akka.actor.Cancellable
import play.api.Play.current
import play.api.libs.concurrent.Execution.Implicits._

object Global extends GlobalSettings {

  var logRunner: Option[Cancellable] = None

  override def onStart(app: Application): Unit = {
    val hostUrl = Play.configuration.getString("log.host.url").get
    Logger.debug(s"hostUrl: $hostUrl")

    val cancellable = Akka.system.scheduler.schedule(0.millisecond, 2.seconds) {
      LogController.clientUrls.foreach { url =>
        val randomMessage = Json.toJson(Log.getMessages(hostUrl))
        WS.url(url).post(randomMessage)
      }
    }

    logRunner = Some(cancellable)
  }

  override def onStop(app: Application): Unit =
    logRunner.foreach(_.cancel)
}
