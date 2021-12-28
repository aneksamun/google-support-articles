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
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class GoogleSupportArticlesController @Inject()(val controllerComponents: ControllerComponents, config: ScrapingConfig)
                                               (implicit ec: ExecutionContext, actorSystem: ActorSystem) extends BaseController {

  def index(): Action[AnyContent] = Action {
    Ok(views.html.index())
  }

  def scrape(): Action[AnyContent] = Action.async {
    def scrapeArticles =
      Scrape(config.googleSupportUri) limitConcurrency config.maxConcurrent

    Functor[Future].map(scrapeArticles) { items =>
      Ok(Json.toJson(items))
    }
  }
}
