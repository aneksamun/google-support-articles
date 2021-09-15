package articles

import akka.actor.ActorSystem
import akka.stream.scaladsl.{Sink, Source}
import net.ruippeixotog.scalascraper.browser.{Browser, JsoupBrowser}
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL._
import scalaz.Functor

import java.net.URI
import scala.concurrent.{ExecutionContext, Future}

object Scrape {

  type Limit = Int

  def apply(source: URI): Builder =
    Builder(source)

  final case class Builder(uri: URI, limit: Option[Limit] = None) {

    def limitConcurrency(threshold: Limit): Builder =
      copy(limit = Some(threshold))
  }

  implicit def builderToProductSupportArticles(builder: Builder)
                                              (implicit ec: ExecutionContext,
                                               actorSystem: ActorSystem): Future[Seq[Product]] = {
    val browser = JsoupBrowser()

    def loadPage = browser.get(builder.uri.toString)

    def extractProducts(doc: Browser#DocumentType) =
      Functor[List].map((doc >> elements("a[data-stats-id]")).toList) { a =>
        (
          a >> text("h3"),
          Uri.combine(builder.uri, a.attr("href"))
        )
      }

    def extractArticles(product: (String, URI)): Future[Product] =
      Future(browser.get(product._2.toString) >> elements("a.article-link")).map(links =>
        Product(
          name = product._1,
          articles = links.map { a =>
            Article(
              headline = a.text,
              link = Uri.combine(builder.uri, a.attr("href"))
            )
          }.toSeq
        )
      )

    Source(loadPage |> extractProducts)
      .mapAsync(builder.limit getOrElse 100)(extractArticles)
      .runWith(Sink.seq)
  }
}
