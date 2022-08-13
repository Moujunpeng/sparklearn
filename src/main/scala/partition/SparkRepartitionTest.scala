package workspare.sparktesst.src.main.scala.partition

import org.apache.spark.{HashPartitioner, SparkConf}
import org.apache.spark.sql.types.{DataTypes, StructField, StructType}
import org.apache.spark.sql.{Row, SparkSession}

import java.util.Properties
import scala.collection.mutable.ArrayBuffer


/**
 * 读取数据默认是一个分区？
 */
object SparkRepartitionTest {

  def main(args: Array[String]): Unit = {

    // 定义sparkSession
    val conf = new SparkConf().setMaster("local[*]").setAppName("mjp_sparksql")
    conf.set("spark.sql.shuffle.partitions","10")
    val session1 = SparkSession.builder().config(conf).getOrCreate()

    val buffer = new ArrayBuffer[Int]()

    for(i <- 1 to 100000){
      buffer.append(1)
    }

    for(i <- 1 to 50){
      buffer.append(i)
    }

    println("input size is " + buffer.size)

    val rdd1 = session1.sparkContext.parallelize(buffer, 4)

    rdd1.foreachPartition(a => (
      println("partition size is : " + a.size)
    ))

    val rdd2 = rdd1.map(a => (a, 1))

    val rdd3 = rdd2.reduceByKey(_ + _)

    rdd3.foreachPartition(a => (
      println("partition size is : " + a.size)
      ))

    println("rdds partittion size is " + rdd3.partitions.size)

    //val rdd3 = rdd2.partitionBy(new HashPartitioner(3))

//    // 创建rdd
//    val rdd = session1.sparkContext.parallelize(buffer, 10)
//
//    val rdd1 = rdd.repartition(5)
//
//    rdd1.foreach(a=>{
//      println(a.get(0))
//    })
//
//    val rdd2 = rdd1.map(a => {
//      (a.getInt(0)+1000,a.getString(1))
//    })

    println("shuffled task num is " + rdd3.count())

    Thread.sleep(1000 * 6000)
  }

}