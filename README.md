# Google Support Articles

![Build status](https://github.com/aneksamun/google-support-articles/actions/workflows/scala.yml/badge.svg)

The ultimate goal of the service is to collate a high-level catalogue of [Google's support articles](https://support.google.com/) for their products, 
displaying collated information in meaningful way that user can interpret easily.   

To be specific, the service is displaying support articles for each product (e.g. [Google Chrome](https://support.google.com/chrome/?hl=en#topic=9796470), [Google Account](https://support.google.com/accounts/?hl=en#topic=3382296) etc.) found on the main page, 
also showing links that users can visit to read article.

![artcles](doc/articles.png?raw=true "Google's support articles")

### How to build?

- Clone project
- Build the project
- Run tests
```
sbt compile
sbt test
```

### Technology stack
- [Scala 2.13.6](http://www.scala-lang.org/) as the main application programming language
- [Play Framework](https://www.playframework.com/) Web Framework for Scala
- [Scala-scraper](https://github.com/ruippeixotog/scala-scraper) a Scala library for scraping content from HTML pages
- [Akka Streams](https://doc.akka.io/docs/akka/current/stream/index.html) streaming API based on Actors
- [ScalaTest](http://www.scalatest.org/) for testing
- [Mock Server](https://www.mock-server.com/) to mock external services for testing purposes