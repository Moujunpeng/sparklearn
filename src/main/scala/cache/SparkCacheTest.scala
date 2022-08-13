package workspare.sparktesst.src.main.scala.cache

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession


/**
 * 读取数据默认是一个分区？
 */
object SparkCacheTest {

  def main(args: Array[String]): Unit = {

    // 定义sparkSession
    val conf = new SparkConf().setMaster("local[*]").setAppName("mjp_sparksql")
    conf.set("spark.sql.shuffle.partitions","10")
    val session1 = SparkSession.builder().config(conf).getOrCreate()
    val df1 = session1.read.format("jdbc").options(Map(
      "url" -> "jdbc:mysql://localhost:3306/mujunpeng",
      "driver" -> "com.mysql.jdbc.Driver",
      "dbtable" -> "table1",
      "user" -> "root",
      "password" -> "113270"
    )).load()

    val rdd = df1.rdd
    rdd.cache()
    val rdd1 = rdd.repartition(6)
    rdd1.foreachPartition(p => p.foreach(item => println(item.get(0))))

    val df2 = session1.read.format("jdbc").options(Map(
      "url" -> "jdbc:mysql://localhost:3306/mujunpeng",
      "driver" -> "com.mysql.jdbc.Driver",
      "dbtable" -> "table2",
      "user" -> "root",
      "password" -> "113270"
    )).load()

    val rdd3 = df2.rdd
    rdd3.cache()
    val rdd4 = df2.repartition(6)
    //rdd4.foreachPartition(p1 => p1.foreach(item1 => println(item1.get(0))))
    rdd4.collect()



    Thread.sleep(1000 * 6000)
  }

}
