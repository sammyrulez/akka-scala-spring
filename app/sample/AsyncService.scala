package sample

import javax.inject.{Qualifier, Named, Inject}

import akka.actor.ActorRef
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import sample.CountingActor.{COUNT, GET}
import akka.actor.{ActorRef, ActorSystem}
import sample.SpringExtension._
import scala.concurrent.duration._
import akka.util.Timeout
import akka.pattern.ask
import scala.concurrent._
import scala.util._
import org.springframework.scala.context.function._
import CountingActor._

import scala.util.{Failure, Success}

/**
 * Created by sam on 24/12/14.
 */

class AsyncService(counter:ActorRef) {

  import Config._

  def count = {
    counter ! COUNT
    counter ! COUNT
    counter ! COUNT

    val result = (counter ? GET).mapTo[Int]
    // print the result
    result onComplete {
      case Success(result) => println(s"Got back $result")
      case Failure(failure) => println(s"Got an exception $failure")
    }
  }

}
