package com.scalatra.torre.keplerr

import org.scalatra.test.specs2._

// For more on Specs2, see http://etorreborre.github.com/specs2/guide/org.specs2.guide.QuickStart.html
class PersonsSpec extends ScalatraSpec { def is =
  "GET / on Persons"                     ^
    "should return status 200"                  ! root200^
                                                p^
  "GET / on Persons with a name param"          ^
    "should return status 200"                  ! nameParamWorks^
                                                p^
  "GET /:slug on Persons"                       ^
    "should return status 200"                  ! slugWorks
                                                end

  implicit val swagger = new PersonsSwagger
  addServlet(new FlowersController, "/persons/*")

  def root200 = get("/persons") {
    status must_== 200
  }

  def nameParamWorks = get("/persons/?name=manuel") {
    status must_== 200
  }

  def slugWorks = get("/persons/manuel") {
    status must_== 200
  }
}
