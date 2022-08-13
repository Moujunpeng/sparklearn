package workspare.sparktesst.src.main.scala.rdd

import org.apache.spark.SparkConf
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{DataTypes, StructField, StructType}

import scala.collection.mutable.ArrayBuffer

object MapPartitionsTest {

  def main(args: Array[String]): Unit = {

    // 创建sparkSession
    val conf = new SparkConf().setMaster("local[*]")
    val session = SparkSession.builder().config(conf).getOrCreate()

    val buffer = new ArrayBuffer[Int]()
    for(i <- 1 to 100){
      buffer += i
    }

    val rdd1 = session.sparkContext.parallelize(buffer, 10)

    val rdd2 = rdd1.mapPartitions(rdd => {
      rdd.map(i => (i, 1))
    })

    //val rdd2 = rdd1.map(i => (i, 1))

    val rdd3 = rdd2.reduceByKey(_ + _)


    val rdd4 = rdd3.map(a => (a._1 + 1))


    val rdd5 = rdd4.take(5)

    Thread.sleep(1000000)


  }

}
