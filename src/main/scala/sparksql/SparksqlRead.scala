package workspare.sparktesst.src.main.scala.sparksql

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession


/**
 * 读取数据默认是一个分区？
 */
object SparksqlRead {

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

    println("input partitions count is " + df1.rdd.partitions.size)

//    val dfrepar = df1.rdd.repartition(3)
//
//    println("modify input partitions count is " + dfrepar.partitions.size)
//
//    dfrepar.map()
//
//    df1.show()

//    val nums = df1.count()
//
//    val rows = df1.take(2)

    df1.createOrReplaceTempView("table1")




//    val session2 = SparkSession.builder().config(conf).getOrCreate()
//
//    val df2 = session2.sql("select * from table1")
//
//    df2.show(10)

    // 第二张表进行交运算
    val df2 = session1.read.format("jdbc").options(Map(
      "url" -> "jdbc:mysql://localhost:3306/mujunpeng",
      "driver" -> "com.mysql.jdbc.Driver",
      "dbtable" -> "table2",
      "user" -> "root",
      "password" -> "113270"
    )).load()

    df2.createOrReplaceTempView("table2")

    val frame = session1.sql("select table1.id,table1.decs,table2.id,table2.name from table1 inner join table2 on table1.id = table2.id")

    frame.show()

    Thread.sleep(1000 * 6000)
  }

}
