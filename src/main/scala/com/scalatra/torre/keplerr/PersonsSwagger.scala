package com.scalatra.torre.keplerr

import org.scalatra.ScalatraServlet
import org.scalatra.swagger.{ApiInfo, NativeSwaggerBase, Swagger}


class ResourcesApp(implicit val swagger: Swagger) extends ScalatraServlet with NativeSwaggerBase

object PersonsSwagger{
  val Info = ApiInfo(
    "The Persons API",
    "Docs for the Persons API",
    "http://keplerr.org",
    "apiteam@keplerr.org",
    "MIT",
    "http://opensource.org/licenses/MIT")
}
class PersonsSwagger extends Swagger(Swagger.SpecVersion, "1", PersonsSwagger.Info)
