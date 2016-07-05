package com.scalatra.torre.keplerr

import org.scalatra._

// Swagger-specific Scalatra imports
import org.scalatra.swagger._

// JSON-related libraries
import org.json4s.{DefaultFormats, Formats}

// JSON handling support from Scalatra
import org.scalatra.json._

class PersonsController(implicit val swagger: Swagger) extends ScalatraServlet with NativeJsonSupport with SwaggerSupport  {

  // Sets up automatic case class to JSON output serialization, required by
  // the JValueResult trait.
  protected implicit val jsonFormats: Formats = DefaultFormats

  // A description of our application. This will show up in the Swagger docs.
  protected val applicationDescription = "The persons API. It exposes operations for browsing and searching lists of persons"

  // Before every action runs, set the content type to be in JSON format.
  before() {
    contentType = formats("json")
  }

  val getPersons =
    (apiOperation[List[Person]]("getPersons")
      summary "Show all persons"
      notes "Shows all the persons in the map. You can search it too."
      parameter queryParam[Option[String]]("name").description("A name to search for"))

  get("/", operation(getPersons)){
    params.get("name") match {
      case Some(name) => PersonData.all filter (_.name.toLowerCase contains name.toLowerCase())
      case None => PersonData.all
    }
  }


  val findBySlug =
    (apiOperation[Person]("findBySlug")
      summary "Find person by its slug"
      parameters (
      pathParam[String]("slug").description("Slug of person that needs to be fetched")))

  get("/:slug", operation(findBySlug)) {
    PersonData.all find (_.slug == params("slug")) match {
      case Some(b) => b
      case None => halt(404)
    }
  }
}


// A Person object to use as a data model
case class Person(slug: String, name: String)

object PersonData {
  var all = List(
    Person("david-c", "David Camargo"),
    Person("manuel-m", "Manuel Montes"),
    Person("amaury-p", "Amaury Prieto"))
}