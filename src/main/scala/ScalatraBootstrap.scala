import com.scalatra.torre.keplerr._
import org.scalatra.LifeCycle
import javax.servlet.ServletContext

class ScalatraBootstrap extends LifeCycle {

  implicit val swagger = new PersonsSwagger

  override def init(context: ServletContext) {
    context.mount(new PersonsController, "/persons", "persons")
    context.mount (new ResourcesApp, "/api-docs")
  }
}
