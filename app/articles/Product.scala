package articles

import play.api.libs.json.{Json, OFormat}

final case class Product(name: String, articles: Seq[Article])

object Product {
  implicit val format: OFormat[Product] =
    Json.format[Product]
}
