package workspare.sparktesst.src.main.scala.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import java.util
import scala.collection.mutable.ListBuffer

object Spark01_RDD_Par {
  def main(args: Array[String]): Unit = {
//    System.setProperty("HADOOP_HOME","admin")
//    System.setProperty("hadoop.home.dir","D:\\mjp1\\workPath")
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("appName")
    val sc = new SparkContext(sparkConf)
    val inputArray = new ListBuffer[Int]()
    for(i <- 1 to 10000){
      inputArray.append(i)
    }
    val rdd1:RDD[Int] = sc.makeRDD(inputArray,60)
    rdd1.map(_*2)

    rdd1.collect().foreach(println)

    rdd1.count()

    Thread.sleep(600000)
    sc.stop()
  }
}
