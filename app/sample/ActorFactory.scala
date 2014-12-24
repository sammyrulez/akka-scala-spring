package sample

import akka.actor.ActorSystem
import org.springframework.context.ApplicationContext

/**
 * Created by sam on 24/12/14.
 */
class ActorFactory(val system:ActorSystem, val ctx: ApplicationContext) {

  def initActor(actorBeanName: String) = {
    implicit val ctxImp = ctx
    val prop = SpringExtentionImpl(system).props(actorBeanName)
    system.actorOf(prop, actorBeanName)
  }

}
