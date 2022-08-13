package workspare.sparktesst.src.main.scala.test

import java.net.ServerSocket

object Executor {
  def main(args: Array[String]): Unit = {
    val socket = new ServerSocket(9999)

    // 接收数据
    val socketClient = socket.accept()



  }
}
