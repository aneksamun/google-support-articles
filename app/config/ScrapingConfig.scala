package config

import com.google.inject.{Inject, Singleton}
import play.api.Configuration

import java.net.URI

@Singleton
class ScrapingConfig @Inject()(config: Configuration) {

  val googleSupportUri = URI create config.get[String]("scraping.google-support-page")

  val maxConcurrent = config.get[Int]("scraping.max-concurrent")
}
