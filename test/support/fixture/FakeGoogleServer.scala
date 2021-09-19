package support.fixture

import org.scalatest.{AsyncTestSuite, BeforeAndAfterAll}
import support.GoogleServerMock

trait FakeGoogleServer extends BeforeAndAfterAll {
  this: AsyncTestSuite =>

  val serverMock = GoogleServerMock()

  override def afterAll(): Unit = serverMock.close()
}
