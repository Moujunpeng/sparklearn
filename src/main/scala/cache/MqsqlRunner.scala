package workspare.sparktesst.src.main.scala.cache

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

import java.util.Properties

object MqsqlRunner {

  def run(prop: Properties,url:String,tableName:String): Unit = {

//    // 定义sparkSession
//    val conf = new SparkConf().setMaster("local[*]").setAppName("mjp_sparksql")
//    val session1 = SparkSession.builder().config(conf).getOrCreate()
//    val df1 = session1.read.jdbc(url,tableName,prop).load()
//
//    df1.show()

  }
}
