package workspare.sparktesst.src.main.scala.streaming

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.{Seconds, StreamingContext}
import scala.collection.mutable.Queue

object SparkStreamingQueue {
  def main(args: Array[String]): Unit = {

    // 创建环境对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkStreaming01")

    val ssc = new StreamingContext(sparkConf, Seconds(5))

    // 获取端口数据
    //val lines:ReceiverInputDStream[String] = ssc.socketTextStream("localhost", 9999)

    // 创建RDD的queue
    val rddQueue = new Queue[RDD[Int]]()

    // 创建DSrream
    val inputStream = ssc.queueStream(rddQueue, false)

    val mappedStream = inputStream.map((_, 1))
    val reducedStream = mappedStream.reduceByKey(_ + _)
    reducedStream.print()

    // 采集器的执行
    ssc.start()

    // 采集器采集生成的RDD
    for(i <- 1 to 5){
      // 往队列中添加rdd
      rddQueue += ssc.sparkContext.makeRDD(1 to 300,10)
      Thread.sleep(2000)
    }

    // 采集器的等待
    ssc.awaitTermination()

  }
}
