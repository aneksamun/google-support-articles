package support

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock._
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.options
import scalaz.Scalaz.ToShowOps
import support.GoogleSupportPage._

import java.net.URL

final case class GoogleServerMock private(server: WireMockServer) extends AutoCloseable {
  server.start()
  configureFor(baseUrl.getProtocol, baseUrl.getHost, baseUrl.getPort)

  lazy val baseUrl = new URL(server.baseUrl())

  stubFor(get(urlEqualTo("/"))
    .willReturn(ok().withBody(GoogleSupportPage.Main.show)))

  stubFor(get(urlEqualTo("/chrome/?hl=en"))
    .willReturn(ok().withBody(GoogleSupportPage.Chrome.show)))

  stubFor(get(urlEqualTo("/mail/?hl=en"))
    .willReturn(ok().withBody(GoogleSupportPage.Gmail.show)))

  stubFor(get(urlEqualTo("/accounts/?hl=en"))
    .willReturn(ok().withBody(GoogleSupportPage.Account.show)))

  stubFor(get(urlEqualTo("/youtube/?hl=en"))
    .willReturn(ok().withBody(GoogleSupportPage.Youtube.show)))

  def close(): Unit = server.shutdown()
}

object GoogleServerMock {

  def apply(): GoogleServerMock =
    GoogleServerMock(new WireMockServer(options().dynamicPort()))
}
