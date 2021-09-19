package support.fixture

import akka.actor.ActorSystem
import org.scalatest.AsyncTestSuite

trait ServerDependencies {
  this: AsyncTestSuite =>

  implicit val actorSystem: ActorSystem = ActorSystem()
}
