package sparkExamples

import org.apache.spark.sql.SparkSession

object SparkSql_2 {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession.builder.master("local").getOrCreate
   /* val salesDF = spark.read.option("header",true).csv("/Users/shivangi/algo/AlgorithmsInScala/src/main/resources/Sales.csv")
    val productsDF = spark.read.option("header",true).csv("/Users/shivangi/algo/AlgorithmsInScala/src/main/resources/Products.csv")
    val sellersDF = spark.read.option("header",true).csv("/Users/shivangi/algo/AlgorithmsInScala/src/main/resources/Sellers.csv")*/
  /*  //q1 = Find out how many orders, how many products and how many sellers are in the data

    salesDF.count()
    productsDF.count()
    sellersDF.count()

    //q2 = How many products have been sold at least once? Which is the product contained in more orders?
    salesDF.filter(salesDF("num_pieces_sold") > 1).show()

    //q3 = #   Output which is the product that has been sold in more orders
      salesDF.groupBy("product_id")
      .agg(
        max("num_pieces_sold").as("pieces sold")).show()

    //q3 = How many distinct products have been sold in each day?
    val productIds = 0 to 13

    salesDF.groupBy("date").agg(approx_count_distinct("product_id")).show()//.pivot("product_id", productIds).count().show()
    salesDF.groupBy("date").pivot("product_id",productIds).count().show()
  //q4 = What is the average revenue of the orders revenue = price of each item * no of items
    val salesProductDF = salesDF.join(productsDF, salesDF("product_id") === productsDF("product_id"))
    val revenueDF = salesProductDF.withColumn("revenue", salesProductDF("num_pieces_sold") * salesProductDF("price"))//.show()
    val avgRevenue = revenueDF.agg(sum("revenue")).first().getDouble(0)/ revenueDF.count()
    println(s"average revenue is ${avgRevenue}")*/

    //q5 = Who are the second most selling and the least selling persons (sellers) for each product
    /*import spark.implicits._

    salesDF.join(sellersDF, sellersDF("seller_id") === salesDF("seller_id")).groupBy("product_id").pivot("seller_name").count().show()

    val byProductIdWindow =  Window.partitionBy("product_id").orderBy($"num_pieces_sold".desc)
    val partitionWindowRank = rank().over(byProductIdWindow)

    salesDF.select($"*", partitionWindowRank as "dense_rank").where($"dense_rank" ===1).show()*/
    val l = List()

    def seqOp(acc : List[Int], ele: Int):List[Int]={
      ???
    }

    def combOp(acc:List[Int], acc1:List[Int]):List[Int]={
      ???
    }

  //  val x  = spark.sparkContext.parallelize(l.map(x => (x,1))).aggregateByKey(List[Int]())(seqOp, combOp)


  }
}
