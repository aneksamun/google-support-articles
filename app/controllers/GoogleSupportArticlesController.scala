package controllers

import akka.actor.ActorSystem
import articles.Product._
import articles.{Article, Product}
import play.api.libs.json.Json

import java.net.URI
//import articles.Scrape._
import com.google.inject.Singleton
import config.ScrapingConfig
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}

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
    //    val builder = Scrape(config.googleSupportUri) limitConcurrency config.maxConcurrent
    //    val items: Future[Seq[Product]] = builder

    Future.successful(
      Ok(
        Json.toJson(
          Seq(
            Product("A", Seq(Article("Ok", URI.create("http://ok.com"))))
          )
        )
      )
    )
  }
}
