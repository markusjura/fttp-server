package models

import org.joda.time.DateTime
import scala.util.Random
import play.api.libs.json.Json

import play.api.Play.configuration

case class Log(date: String = DateTime.now.toString("YYYY-MM-DD HH:mm:ss.SSS"),
               host: String, message: String, level: String)

object Log {
  implicit val logFormat = Json.format[Log]

  val messages: Seq[(String, String)] = Seq(
    ("User has been successfully created", Info.toString),
    ("NullPointerException occured.", Error.toString),
    ("Actor 'user' has been created", Info.toString),
    ("Actor 'user' has been stopped", Warning.toString),
    ("Actor 'user' received a message", Debug.toString),
    ("Just another log message", Debug.toString),
    ("This is an important step to log.", Info.toString),
    ("Not sure why we are logging this", Info.toString),
    ("Rich is the best..", Info.toString),
    ("I hope Heiko will like this.", Info.toString),
    ("Server 'main' is not responding..", Fatal.toString),
    ("Product has been successfully created", Info.toString),
    ("IllegalArgumentException occured.", Error.toString),
    ("Actor 'product' has been created", Info.toString),
    ("Actor 'product' has been stopped", Warning.toString),
    ("Actor 'product' received a message", Debug.toString),
    ("Message for testing.. Remove when test finished.", Debug.toString),
    ("Actor 'card' has been created", Info.toString),
    ("Actor 'card' has been stopped", Warning.toString),
    ("Actor 'card' received a message", Info.toString)
  )

  def getMessages(hostUrl: String): Seq[Log] = {
    val newMessageSize = Random.nextInt(4)
    1 to newMessageSize map { _ =>
      val randomMessage = messages(Random.nextInt(messages.size))
      Log(host = hostUrl, message = randomMessage._1, level = randomMessage._2)
    }
  }
}

trait Level { def toString: String }
case object Debug extends Level { override def toString: String = "DEBUG" }
case object Info extends Level { override def toString: String = "INFO" }
case object Warning extends Level { override def toString: String = "WARNING" }
case object Error extends Level { override def toString: String = "ERROR" }
case object Fatal extends Level { override def toString: String = "FATAL" }
