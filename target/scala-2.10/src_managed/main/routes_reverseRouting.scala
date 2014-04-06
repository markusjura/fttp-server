// @SOURCE:/Users/mj/workspace/log-server/conf/routes
// @HASH:94439d9892c24c8afa709cd38ff2daf2113bcb24
// @DATE:Sun Apr 06 17:36:25 CEST 2014

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._


import Router.queryString


// @LINE:9
// @LINE:6
// @LINE:5
package controllers {

// @LINE:6
// @LINE:5
class ReverseLogController {
    

// @LINE:6
def unsubscribe(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "unsubscribe")
}
                                                

// @LINE:5
def subscribe(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "subscribe")
}
                                                
    
}
                          

// @LINE:9
class ReverseAssets {
    

// @LINE:9
def at(file:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                
    
}
                          
}
                  


// @LINE:9
// @LINE:6
// @LINE:5
package controllers.javascript {

// @LINE:6
// @LINE:5
class ReverseLogController {
    

// @LINE:6
def unsubscribe : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.LogController.unsubscribe",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "unsubscribe"})
      }
   """
)
                        

// @LINE:5
def subscribe : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.LogController.subscribe",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "subscribe"})
      }
   """
)
                        
    
}
              

// @LINE:9
class ReverseAssets {
    

// @LINE:9
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        
    
}
              
}
        


// @LINE:9
// @LINE:6
// @LINE:5
package controllers.ref {


// @LINE:6
// @LINE:5
class ReverseLogController {
    

// @LINE:6
def unsubscribe(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.LogController.unsubscribe(), HandlerDef(this, "controllers.LogController", "unsubscribe", Seq(), "POST", """""", _prefix + """unsubscribe""")
)
                      

// @LINE:5
def subscribe(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.LogController.subscribe(), HandlerDef(this, "controllers.LogController", "subscribe", Seq(), "POST", """""", _prefix + """subscribe""")
)
                      
    
}
                          

// @LINE:9
class ReverseAssets {
    

// @LINE:9
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      
    
}
                          
}
        
    