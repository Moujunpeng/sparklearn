package workspare.sparktesst.src.main.scala.sparksql

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object SparkReadGlobalView {
  def main(args: Array[String]): Unit = {

    // 定义sparkconf
    val conf = new SparkConf().setMaster("local[*]").setAppName("mjp_sparksql")
    // 创建sparksession
    val session = SparkSession.builder().config(conf).getOrCreate()

    val frame = session.sql("select * from table1")

    frame.show()

    Thread.sleep(1000 * 600)
  }
}
