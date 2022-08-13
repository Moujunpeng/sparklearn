package workspare.sparktesst.src.main.scala.test

import java.net.Socket

object Driver {

  def main(args: Array[String]): Unit = {
    // 连接服务器
    val socket = new Socket("localhost",9999)
  }
}
