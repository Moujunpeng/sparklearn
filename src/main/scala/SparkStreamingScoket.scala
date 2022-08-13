package workspare.sparktesst.src.main.scala

import org.apache.spark.SparkConf
import org.apache.spark.sql.catalyst.expressions.Second
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.dstream.ReceiverInputDStream

object SparkStreamingScoket {
  def main(args: Array[String]): Unit = {

    // 创建环境对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkStreaming01")

    val ssc = new StreamingContext(sparkConf, Seconds(5))

    // 创建Dstream
    val lines:ReceiverInputDStream[String] = ssc.socketTextStream("localhost", 9999)

    val words = lines.flatMap(_.split(" "))
    val wordToOne = words.map((_, 1))
    val output = wordToOne.reduceByKey(_ + _)
    output.print()

    // 采集器的执行
    ssc.start()

    // 采集器的等待
    ssc.awaitTermination()

  }
}
