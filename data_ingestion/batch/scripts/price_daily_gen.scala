//Load data
val args = sc.getConf.get("spark.driver.args").split("\\s+")
val priceDataPath=args(0)
val priceDF = spark.read.options(Map("header"->"true","inferSchema"->"true")).csv(s"$priceDataPath/minute_price")
priceDF.createOrReplaceTempView("prices")

//Get the close price per day
val todayPrice = spark.sql("""
with prices_rank as(
  SELECT *, dense_rank() over (partition by date(time) order by time desc,price desc) as rank 
  FROM prices
)
select price,date(time) as time,receive_dt,rank from prices_rank where rank = 1  order by time desc
""")
//Save to daily price folder
todayPrice.dropDuplicates().coalesce(1).write.options(Map("header"->"true")).mode("overwrite").partitionBy("receive_dt").csv(s"$priceDataPath/daily_price/")
System.exit(0)