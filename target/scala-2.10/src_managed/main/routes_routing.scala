// @SOURCE:/Users/mj/workspace/log-server/conf/routes
// @HASH:94439d9892c24c8afa709cd38ff2daf2113bcb24
// @DATE:Sun Apr 06 17:36:25 CEST 2014


import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._


import Router.queryString

object Routes extends Router.Routes {

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:5
private[this] lazy val controllers_LogController_subscribe0 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("subscribe"))))
        

// @LINE:6
private[this] lazy val controllers_LogController_unsubscribe1 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("unsubscribe"))))
        

// @LINE:9
private[this] lazy val controllers_Assets_at2 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
        
def documentation = List(("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """subscribe""","""controllers.LogController.subscribe"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """unsubscribe""","""controllers.LogController.unsubscribe"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]] 
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:5
case controllers_LogController_subscribe0(params) => {
   call { 
        invokeHandler(controllers.LogController.subscribe, HandlerDef(this, "controllers.LogController", "subscribe", Nil,"POST", """""", Routes.prefix + """subscribe"""))
   }
}
        

// @LINE:6
case controllers_LogController_unsubscribe1(params) => {
   call { 
        invokeHandler(controllers.LogController.unsubscribe, HandlerDef(this, "controllers.LogController", "unsubscribe", Nil,"POST", """""", Routes.prefix + """unsubscribe"""))
   }
}
        

// @LINE:9
case controllers_Assets_at2(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
   }
}
        
}

}
     