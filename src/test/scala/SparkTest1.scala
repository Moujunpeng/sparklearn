package workspare.sparktesst.src.test.scala

import org.apache.spark.SparkConf
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SaveMode, SparkSession}
import org.scalatest.funsuite.AnyFunSuite

import scala.collection.JavaConverters._


class SparkTest1 extends AnyFunSuite{

    test("RddWriteToMysql"){
      // 创建sparksession
      val sparkWriteToMysql = new SparkConf().setMaster("local").setAppName("SparkWriteToMysql")
      val session = SparkSession.builder().config(sparkWriteToMysql).getOrCreate()

      // 创建输入数据 通过dataFrame
      val id = StructField("id", IntegerType, true)
      val name = StructField("decs", StringType, true)

      val structType = StructType(List(id,name))

      val row1 = Row.fromSeq(Seq(101,"mjp101"))
      val row2 = Row.fromSeq(Seq(102, "mjp102"))
      val row3 = Row.fromSeq(Seq(103, "mjp103"))

      // 利用df写入到mysql数据库中
      val df = session.createDataFrame(List(row1, row2,row3).asJava, structType)
      val l = df.count()

      val unit = df.write.format("jdbc").options(Map(
        "url" -> "jdbc:mysql://localhost:3306/mujunpeng",
        "driver" -> "com.mysql.jdbc.Driver",
        "dbtable" -> "table1",
        "user" -> "root",
        "password" -> "113270")).mode(SaveMode.Append)
        .save()

      Thread.sleep(360*1000)
    }

  test("testMap"){
    // 创建sparksession
    val sparkWriteToMysql = new SparkConf().setMaster("local").setAppName("SparkWriteToMysql")
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
