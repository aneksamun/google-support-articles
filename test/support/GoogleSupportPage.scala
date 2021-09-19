package support

import scalaz.{Cord, Show}

import scala.io.Source
import scala.util.Using

sealed abstract class GoogleSupportPage(fileName: String) {

  override def toString: String =
    Using.resource(getClass.getResourceAsStream(s"/google_support/$fileName")) { stream =>
      Source.fromInputStream(stream).mkString
    }
}

object GoogleSupportPage {

  case object Main extends GoogleSupportPage("main.html")
  case object Chrome extends GoogleSupportPage("chrome.html")
  case object Gmail extends GoogleSupportPage("gmail.html")
  case object YouTube extends GoogleSupportPage("youtube.html")
  case object Account extends GoogleSupportPage("account.html")

  implicit def showPage[A <: GoogleSupportPage]: Show[A] = (page: A) =>
    Cord(page.toString)

  implicit def cordToString(cord: Cord): String =
    cord.shows
}
