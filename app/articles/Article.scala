package articles

import play.api.libs.json.{Json, OFormat}

import java.net.URI

final case class Article(headline: String, link: URI)

object Article {
  implicit val format: OFormat[Article] =
    Json.format[Article]
}
