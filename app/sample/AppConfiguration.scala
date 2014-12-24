package sample

import akka.actor.ActorSystem
import org.springframework.context.ApplicationContext
import org.springframework.scala.context.function.FunctionalConfiguration
import org.springframework.beans.factory.config.BeanDefinition



class AppConfiguration extends FunctionalConfiguration {
  /**
   * Load implicit context
   */
  implicit val ctx = beanFactory.asInstanceOf[ApplicationContext]

  importXml("classpath:/myContext.xml")

  /**
   * Actor system singleton for this application.
   */
  val actorSystem = bean() {
    val system = ActorSystem("AkkaScalaSpring")
    // initialize the application context in the Akka Spring Extension
    SpringExtentionImpl(system)
    system
  }

  /*val countingService = bean("countingService") {
    new CountingService
  }*/

  val countingActor = bean("countingActor",  scope = BeanDefinition.SCOPE_PROTOTYPE) {
    val ca = new CountingActor
    ca.countingService = ctx.getBean(classOf[CountingService])
    ca
  }

  val actorFactory = bean("countingActorFactory", scope = BeanDefinition.SCOPE_SINGLETON) {

     new ActorFactory(actorSystem(),ctx)
  }

  /*val asyncService = bean(){
    new AsyncService(actorFactory().initActor("countingActor"))
  }*/



}