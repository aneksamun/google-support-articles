package support

import org.mockserver.integration.ClientAndServer
import org.mockserver.integration.ClientAndServer.startClientAndServer
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response
import play.api.http.Status.OK
import scalaz.Scalaz.ToShowOps
import support.GoogleSupportPage._

import java.net.URL

class GoogleServerMock private(server: ClientAndServer) extends AutoCloseable {
  val baseUrl = new URL(s"http://localhost:${server.getPort}")

  server
    .when(request().withPath("/"))
    .respond(response()
      .withBody(GoogleSupportPage.Main.show)
      .withStatusCode(OK))

  server
    .when(request().withPath("/chrome"))
    .respond(response()
      .withBody(GoogleSupportPage.Chrome.show)
      .withStatusCode(OK))

  server
    .when(request().withPath("/mail"))
    .respond(response()
      .withBody(GoogleSupportPage.Gmail.show)
      .withStatusCode(OK))

  server
    .when(request().withPath("/accounts"))
    .respond(response()
      .withBody(GoogleSupportPage.Account.show)
      .withStatusCode(OK))

  server
    .when(request().withPath("/youtube"))
    .respond(response()
      .withBody(GoogleSupportPage.YouTube.show)
      .withStatusCode(OK))

  def close(): Unit = server.close()
}

object GoogleServerMock {

  def apply(): GoogleServerMock =
    new GoogleServerMock(startClientAndServer())
}
