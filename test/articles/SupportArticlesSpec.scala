package articles

import org.scalatest.{EitherValues, GivenWhenThen}
import org.scalatest.featurespec.AsyncFeatureSpec
import org.scalatest.matchers.should.Matchers
import support.fixture.{FakeGoogleServer, ServerDependencies}

import scala.concurrent.Future

class SupportArticlesSpec extends AsyncFeatureSpec
  with FakeGoogleServer
  with ServerDependencies
  with GivenWhenThen
  with EitherValues
  with Matchers {

  Feature("Scraping Google Support articles") {

    info("As system user")
    info("I wish to see the list of articles which available for Google products")
    info("So that I can build catalogue")

    Scenario("Listing articles") {
      When("I make request to scrape Google Help page")
      val articles: Future[Seq[Product]] = Scrape(serverMock.baseUrl.toURI)

      Then("it should provide the list of articles for available Google product")
      articles.map(_ should be(
        Vector(
          Product("Google Chrome", List(
            Article("Use Chrome at home", Uri(s"http://localhost:${serverMock.baseUrl.getPort}/chrome/answer/9795973?hl=en&ref_topic=9796470")),
            Article("Update Google Chrome", Uri(s"http://localhost:${serverMock.baseUrl.getPort}/chrome/answer/95414?hl=en&ref_topic=7438008")),
            Article("Remove unwanted ads, pop-ups & malware", Uri(s"http://localhost:${serverMock.baseUrl.getPort}/chrome/answer/2765944?hl=en&ref_topic=7438008")),
            Article("Clear browsing data", Uri(s"http://localhost:${serverMock.baseUrl.getPort}/chrome/answer/2392709?hl=en&ref_topic=7438008")),
            Article("Fix \"Aw, Snap!\" page crashes and other page loading errors", Uri(s"http://localhost:${serverMock.baseUrl.getPort}/chrome/answer/95669?hl=en&ref_topic=7438008")),
            Article("Flash Player is no longer available", Uri(s"http://localhost:${serverMock.baseUrl.getPort}/chrome/answer/6258784?hl=en&ref_topic=7438008")),
          )),
          Product("Google Account", List(
            Article("Create a Google Account", Uri(s"http://localhost:${serverMock.baseUrl.getPort}/accounts/answer/27441?hl=en&ref_topic=3382296")),
            Article("Create a strong password & a more secure account", Uri(s"http://localhost:${serverMock.baseUrl.getPort}/accounts/answer/32040?hl=en&ref_topic=3382296")),
            Article("Verify your account", Uri(s"http://localhost:${serverMock.baseUrl.getPort}/accounts/answer/114129?hl=en&ref_topic=3382296")),
            Article("Control what others see about you across Google services", Uri(s"http://localhost:${serverMock.baseUrl.getPort}/accounts/answer/6304920?hl=en&ref_topic=3382296")),
            Article("Someone changed your password", Uri(s"http://localhost:${serverMock.baseUrl.getPort}/accounts/answer/58585?hl=en&ref_topic=3382296")),
            Article("Be ready to find a lost Android device", Uri(s"http://localhost:${serverMock.baseUrl.getPort}/accounts/answer/3265955?hl=en&ref_topic=3382296")),
            Article("Manage your Location History", Uri(s"http://localhost:${serverMock.baseUrl.getPort}/accounts/answer/3118687?hl=en&ref_topic=3382296")),
            Article("Set up a recovery phone number or email address", Uri(s"http://localhost:${serverMock.baseUrl.getPort}/accounts/answer/183723?hl=en&ref_topic=3382296")),
            Article("Turn cookies on or off", Uri(s"http://localhost:${serverMock.baseUrl.getPort}/accounts/answer/61416?hl=en&ref_topic=3382296")),
            Article("How to recover your Google Account or Gmail", Uri(s"http://localhost:${serverMock.baseUrl.getPort}/accounts/answer/7682439?hl=en&ref_topic=3382296")),
            Article("Find & control your Web & App Activity", Uri(s"http://localhost:${serverMock.baseUrl.getPort}/accounts/answer/54068?hl=en&ref_topic=3382296")),
            Article("Control the ads you see", Uri(s"http://localhost:${serverMock.baseUrl.getPort}/accounts/answer/2662856?hl=en&ref_topic=3382296"))
          )),
          Product("YouTube", List(
            Article("Understand your choices as a family", Uri(s"http://localhost:${serverMock.baseUrl.getPort}/youtube/answer/10315420?hl=en&ref_topic=10314939")),
            Article("What is a supervised experience on YouTube?", Uri(s"http://localhost:${serverMock.baseUrl.getPort}/youtube/answer/10314940?hl=en&ref_topic=10314939")),
            Article("Get started with supervised accounts", Uri(s"http://localhost:${serverMock.baseUrl.getPort}/youtube/answer/10314074?hl=en&ref_topic=10314939")),
            Article("Content settings for families using supervised experiences", Uri(s"http://localhost:${serverMock.baseUrl.getPort}/youtube/answer/10315823?hl=en&ref_topic=10314939")),
            Article("FAQs for parents about supervised accounts", Uri(s"http://localhost:${serverMock.baseUrl.getPort}/youtube/answer/10315824?hl=en&ref_topic=10314939")),
            Article("Info for creators about a supervised experience on YouTube", Uri(s"http://localhost:${serverMock.baseUrl.getPort}/youtube/answer/10315825?hl=en&ref_topic=10314939")),
          )),
          Product("Gmail", List(
            Article("Start or join a video call from Gmail", Uri(s"http://localhost:${serverMock.baseUrl.getPort}/mail/answer/9822902?hl=en&ref_topic=9824892"))
          ))
        )
      ))
    }
  }
}
