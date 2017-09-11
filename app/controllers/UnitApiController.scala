package controllers

import javax.inject._

import play.api._
import play.api.mvc._
import services._

import scala.concurrent._

@Singleton
class UnitApiController @Inject()(
    cc: ControllerComponents,
    unitApiService: UnitApiService,
)(implicit executor: ExecutionContext)
    extends AbstractController(cc)
    with PrettyJsonResult {

  def utterance = (Action async parse.json) { implicit request =>
    val query = (request.body \ "query").as[String]
    val sessionId = (request.body \ "sessionId").as[String]

    Logger trace s"unit utterance / sessionId = '$sessionId', query = '$query'"

    unitApiService utterance (query, sessionId) map (Ok(_))
  }

}
