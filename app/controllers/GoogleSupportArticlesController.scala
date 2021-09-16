package controllers

import akka.actor.ActorSystem
import articles.Scrape
import articles.Scrape._
import com.google.inject.Singleton
import config.ScrapingConfig
import play.api.libs.json.Json
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import scalaz.Functor
import scalaz.Scalaz.futureInstance

import javax.inject.Inject
import scala.concurrent.Future

@Singleton
class GoogleSupportArticlesController @Inject()(val controllerComponents: ControllerComponents, config: ScrapingConfig)
  extends BaseController {

  implicit val executionContext = controllerComponents.executionContext
  implicit val actorSystem = ActorSystem()

  def index(): Action[AnyContent] = Action { implicit request =>
    Ok(views.html.index())
  }

  def scrape(): Action[AnyContent] = Action.async { implicit request =>
    def scrapeArticles =
      Scrape(config.googleSupportUri) limitConcurrency config.maxConcurrent

    Functor[Future].map(scrapeArticles) { items =>
      Ok(Json.toJson(items))
    }
  }
}
