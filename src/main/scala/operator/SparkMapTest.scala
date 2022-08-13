package workspare.sparktesst.src.main.scala.operator

import org.apache.spark.{SparkConf, SparkContext}

object SparkMapTest {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[*]").setAppName("spark")
    val context = new SparkContext(conf)

    val rdd = context.makeRDD(List(1, 2, 3, 4, 5), 3)
    val rddTransfer = rdd.map(_ * 2)
    rddTransfer.collect().foreach(println)

    Thread.sleep(100000)
  }


}
