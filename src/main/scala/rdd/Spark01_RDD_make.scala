package workspare.sparktesst.src.main.scala.rdd

import org.apache.spark.SparkConf
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{DataTypes, StructField, StructType}

import scala.collection.mutable.ArrayBuffer

object Spark01_RDD_make {

  def main(args: Array[String]): Unit = {

    // 创建sparkSession
    val conf = new SparkConf().setMaster("local[*]")
    val session = SparkSession.builder().config(conf).getOrCreate()

    val buffer = new ArrayBuffer[Row]()
    for(i <- 1 to 1000){
      buffer += Row(1,"task" + i)
    }

    val schema = StructType(Seq(StructField("id", DataTypes.IntegerType),
      StructField("name",DataTypes.StringType)))

    // 创建rdd
    val rdd = session.sparkContext.parallelize(buffer, 10)

    val rows = rdd.collect()

    // 创建dataframe
    val df = session.createDataFrame(rdd, schema)

    df.show(10)

    Thread.sleep(1000000)
  }

}
