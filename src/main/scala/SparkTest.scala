package workspare.sparktesst.src.main.scala

import org.apache.spark.SparkConf
import org.apache.spark.sql.{Row, SaveMode, SparkSession}
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

object SparkTest {

  def main(args: Array[String]): Unit = {
    // 创建sparksession
    val sparkWriteToMysql = new SparkConf().setMaster("local").setAppName("SparkWriteToMysql")
    sparkWriteToMysql.set("spark.driver.host", "127.0.0.1")
    sparkWriteToMysql.set("spark.port.maxRetries","1000")
    val session = SparkSession.builder().config(sparkWriteToMysql).getOrCreate()

    val df = session.read.format("jdbc").options(Map(
      "url" -> "jdbc:mysql://localhost:3306/mujunpeng",
      "driver" -> "com.mysql.jdbc.Driver",
      "dbtable" -> "table1",
      "user" -> "root",
      "password" -> "113270")).load()

    // id + 100 map(iter=> (iter._1,iter._2))
    val transferRdd = df.rdd.map(s => {
      val i = s.getInt(0)
      val str = s.getString(1)
      Row(i+1,str)
    })

    val id = StructField("id", IntegerType, true)
    val name = StructField("name", StringType, true)
    val structType = StructType(List(id,name))
    val outputTransfer = session.createDataFrame(transferRdd, structType)

    val value = outputTransfer.show()

    val unit = outputTransfer.write.format("jdbc").options(Map(
      "url" -> "jdbc:mysql://localhost:3306/mujunpeng",
      "driver" -> "com.mysql.jdbc.Driver",
      "dbtable" -> "table2",
      "user" -> "root",
      "password" -> "113270")).mode(SaveMode.Append)
      .save()

    Thread.sleep(360*1000)
  }

}
