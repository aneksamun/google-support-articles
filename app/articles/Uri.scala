package articles

import java.net.URI

object Uri {

  def combine(root: URI, path: String): URI =
    root resolve path

  def apply(value: String): URI = URI create value
}
