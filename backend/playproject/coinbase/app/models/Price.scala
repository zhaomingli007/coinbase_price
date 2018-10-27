package models

import java.util.Date

import play.api.libs.json.{JsPath, Writes}
import play.api.libs.functional.syntax._

case class Price(price: Double, time: Date)

object Price {
  implicit val priceWrites: Writes[Price] = (
    (JsPath \ "price").write[Double] and
      (JsPath \ "time").write[Date]
    ) (unlift(Price.unapply))

}


