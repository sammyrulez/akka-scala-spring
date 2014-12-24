package sample


import akka.actor.{ActorRef, ActorSystem}
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext
import sample.SpringExtension._
import scala.concurrent.duration._
import akka.util.Timeout
import akka.pattern.ask
import scala.concurrent._
import scala.util._
import org.springframework.scala.context.function._
import CountingActor._


object Main extends App {
  // create a spring context
  implicit val ctx = FunctionalConfigApplicationContext(classOf[AppConfiguration])

  //context = new ClassPathXmlApplicationContext("classpath:conf/*-Context.xml");

  import Config._

  // get hold of the actor system
    val system = ctx.getBean(classOf[ActorSystem])

  /*  val prop = SpringExtentionImpl(system).props("countingActor")

    // use the Spring Extension to create props for a named actor bean
    val counter = system.actorOf(prop, "counter")

  val counter = ctx.getBean(classOf[AsyncService]).initActor("countingActor")

  // tell it to count three times
  counter ! COUNT
  counter ! COUNT
  counter ! COUNT

  val result = (counter ? GET).mapTo[Int]
  // print the result
  result onComplete {
    case Success(result) => println(s"Got back $result")
    case Failure(failure) => println(s"Got an exception $failure")
  }

*/
  val counter = ctx.getBean(classOf[AsyncService])
  counter.count

  system.shutdown
  system.awaitTermination
}
