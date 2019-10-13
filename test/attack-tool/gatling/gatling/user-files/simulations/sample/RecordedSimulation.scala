package sample

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.HeaderNames
import io.gatling.http.HeaderValues
import scala.concurrent.duration._

class RecordedSimulation extends Simulation {
  val httpConf = http.baseUrl("http://umejima-vpc-elb-394473554.ap-northeast-1.elb.amazonaws.com")

  val scn = scenario("[Rest] Scenario")
    .exec(
        http("Get")
            .get("/app/rest/get")
            .check(jsonPath("$.id").saveAs("myResponseId"))
    ).exec(
        http("Post")
            .post("/app/rest/post")
            .body(StringBody("""{"id":${myResponseId}, "value":"sample"}"""))
            .asJson
    ).exec(
        http("Get Binary")
            .get("/app/rest/getBinary")
    ).exec(
        http("Post Binary")
            .post("/app/rest/postBinary")
            .header(HeaderNames.ContentType, HeaderValues.ApplicationOctetStream)
            .body(RawFileBody("C:\\gatling-3.2.1\\user-files\\simulations\\sample\\postBinary\\sample"))
    )

  setUp(scn.inject(
    atOnceUsers(10),
    rampUsers(10) during (5 seconds),
  ).protocols(httpConf) )
}
