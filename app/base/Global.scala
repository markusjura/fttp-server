package base

import controllers.LogController
import play.api._
import models.Log
import play.api.libs.concurrent.Akka
import play.api.libs.json.Json
import play.api.libs.ws.WS
import scala.concurrent.duration._
import akka.actor.Cancellable
import play.api.Play.current
import play.api.libs.concurrent.Execution.Implicits._

object Global extends GlobalSettings {

  private var logRunner: Option[Cancellable] = None

  override def onStart(app: Application): Unit = {
    // Retrieve host url of log server. Default is "http://localhost:8000"
    // If you want to start a second log server, run "activator 'run 8000" -Dlog.host.url="http://localhost:8001"' on the command line
    val hostUrl = Play.configuration.getString("log.host.url").get

    // Sends every 2 seconds random messages to the subscribed log clients
    val cancellable = Akka.system.scheduler.schedule(0.millisecond, 2.seconds) {
      LogController.clientUrls.foreach { url =>
        val randomMessage = Json.toJson(Log.getMessages(hostUrl))
        WS.url(url).post(randomMessage)
      }
    }

    logRunner = Some(cancellable)
  }

  // Cancels the Akka scheduler if the application stops
  override def onStop(app: Application): Unit =
    logRunner.foreach(_.cancel)
}
