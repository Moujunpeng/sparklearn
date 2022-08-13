package workspare.sparktesst.src.main.scala.sparksql

import org.apache.spark.SparkConf
import org.apache.spark.sql.types.{DataType, DataTypes, StructField, StructType}
import org.apache.spark.sql.{Row, SparkSession}

import java.util.Properties
import scala.collection.mutable.ArrayBuffer


/**
 * 读取数据默认是一个分区？
 */
object SparksqlWrite {

  def main(args: Array[String]): Unit = {

    // 定义sparkSession
    val conf = new SparkConf().setMaster("local[*]").setAppName("mjp_sparksql")
    conf.set("spark.sql.shuffle.partitions","10")
    val session1 = SparkSession.builder().config(conf).getOrCreate()

    val buffer = new ArrayBuffer[Row]()

    for(i <- 1 to 1000){
      buffer.append(Row(1,"" + i))
    }

    // 创建rdd
    val rdd = session1.sparkContext.parallelize(buffer, 10)

    val structType = StructType(Seq(StructField("id", DataTypes.IntegerType), StructField("name", DataTypes.StringType)))

    val df = session1.createDataFrame(rdd, structType)

    val prop = new Properties()
    prop.setProperty("user","root")
    prop.setProperty("password","113270")
    prop.setProperty("driver","com.mysql.jdbc.Driver")

    import org.apache.spark.sql.SaveMode
    df.write.mode(SaveMode.Overwrite).jdbc("jdbc:mysql://localhost:3306/mujunpeng", "jointable1", prop)

    Thread.sleep(1000 * 6000)
  }

}