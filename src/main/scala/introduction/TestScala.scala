package workspare.sparktesst.src.main.scala.introduction

import workspare.sparktesst.src.main.scala.introduction.Car


object TestScala {

  def main(args: Array[String]): Unit = {

    for(i <- 1.to(10)){
      print(s"$i is ")
    }

    println("============")

    val car = new Car()
    car turn "0000"

    (1.to(10)).foreach(i => println(s" input is $i"))

  }

}