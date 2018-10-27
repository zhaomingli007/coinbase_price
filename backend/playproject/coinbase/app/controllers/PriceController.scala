package controllers

import java.text.SimpleDateFormat
import java.util.Date

import javax.inject.{Inject, Singleton}
import models.Price
import models.dao.PricesDAO
import play.api.db.Database
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}

@Singleton
class PriceController @Inject()(db: Database,cc: ControllerComponents)(implicit assetsFinder: AssetsFinder)
  extends AbstractController(cc) {
  val sdf = new SimpleDateFormat("yyyy-MM-dd")


  def getPricesLastWeek() = Action {
    val prices = PricesDAO.priceLastWeek(db)
    Ok(Json.obj("prices" -> prices))
  }

  def getPricesLastMonth() = Action {
    val prices = PricesDAO.priceLastMonth(db)
    Ok(Json.obj("prices" -> prices))
  }

  def getPricesBetween(start: Date, end: Date) = Action {
    val prices = PricesDAO.priceBetween(db,start,end)
    Ok(Json.obj("prices" -> prices))
  }

  def getAverageXDaysPricesBetween(xday: Int, start: Date, end: Date) = Action {
    val prices = PricesDAO.getAverageXDaysPricesBetween(db,xday,start,end)
    Ok(Json.obj("prices" -> prices))

  }

  def getForecastedPrices() = Action {
    val prices = PricesDAO.getForecastPrice(db)
    Ok(Json.obj("prices" -> prices))

  }
}
