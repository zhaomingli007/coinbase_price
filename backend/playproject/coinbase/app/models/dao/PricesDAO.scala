package models.dao

import java.util.Date


import models.Price
import play.api.db.{Database}
import anorm._

object PricesDAO {
  val parser: RowParser[Price] = Macro.namedParser[Price]

  def priceLastWeek(database: Database):List[Price]={
    val sql =
      """
        |SELECT `price`,`time`
        |FROM prices
        |WHERE time >= curdate() - INTERVAL DAYOFWEEK(curdate())+6 DAY
        |AND time < curdate() - INTERVAL DAYOFWEEK(curdate())-1 DAY
      """.stripMargin
    getPrices(database,sql)
  }

  def priceLastMonth(database: Database):List[Price]={
    val sql =
      """
        |SELECT `price`,`time`
        |FROM prices
        |WHERE time >= curdate() - INTERVAL DAYOFMONTH(curdate())+29 DAY
        |AND time < curdate() - INTERVAL DAYOFMONTH(curdate())-1 DAY
      """.stripMargin
    getPrices(database,sql)
  }


  def priceBetween(database: Database,start:Date,end:Date):List[Price]={
    val sql =
      """
        |SELECT `price`,`time`
        |FROM prices
        |WHERE time >= {start} and time <={end}
      """.stripMargin
      database.withConnection(true) { implicit c =>
        SQL(sql).on(
          "start"->start,
          "end"->end
        ).as(parser.*)

    }
  }

  def getAverageXDaysPricesBetween(database: Database,xday:Int, start: Date, end: Date):List[Price]={
    val sql =
      """
        |SELECT  `time`,
        |avg(`price`) over(order by `time` ROWS BETWEEN CURRENT ROW and {xday} FOLLOWING ) as price
        |FROM prices
        |WHERE time >= {start} and time <={end}
      """.stripMargin
      database.withConnection(true) { implicit c =>
        SQL(sql).on("xday"->xday,
          "start"->start,
          "end"->end
        ).as(parser.*)
      }
  }

  def getForecastPrice(database: Database):List[Price]={
    val sql =
      """
        |SELECT `price`,`time`
        |FROM forecast_price
      """.stripMargin
    getPrices(database,sql)
  }




  def getPrices(database: Database,sqlStr:String): List[Price] = {
    database.withConnection(true) { implicit c =>
      SQL(sqlStr).as(parser.*)

    }
  }

}
