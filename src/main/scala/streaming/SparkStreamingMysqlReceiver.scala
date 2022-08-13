package workspare.sparktesst.src.main.scala.streaming

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.receiver.Receiver

import java.sql.{Connection, DriverManager, ResultSet, Statement}

object SparkStreamingMysqlReceiver {
  def main(args: Array[String]): Unit = {

    // 创建环境对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkStreaming01")

    val ssc = new StreamingContext(sparkConf, Seconds(5))

    // 获取端口数据
    val lines:ReceiverInputDStream[String] = ssc.receiverStream(new MyReceiver)

    lines.print()

    // 采集器的执行
    ssc.start()

    // 采集器的等待
    ssc.awaitTermination()

    }


  // 自定义数据采集器
  class MyReceiver extends Receiver[String](StorageLevel.MEMORY_ONLY){

    private var flag = true

    override def onStart(): Unit = {
      new Thread(new Runnable {
        override def run(): Unit = {

          while(flag){

            // 连接mysql数据库
            val aClass = Class.forName("com.mysql.cj.jdbc.Driver")
            val connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mujunpeng", "root", "113270")
            val statement = connection.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.CONCUR_UPDATABLE)
            val resultSet = statement.executeQuery("select * from table1")
            var message = ""
            while(resultSet.next()){
              val decs = resultSet.getString(2)
              message += (decs + " ")
            }
            store(message)
            Thread.sleep(1000)
          }

        }
      }).start()

    }

    override def onStop(): Unit = {
      flag = false
    }
  }

  }

