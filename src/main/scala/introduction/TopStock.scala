package workspare.sparktesst.src.main.scala.introduction

import java.util
import scala.collection.mutable
import scala.collection.JavaConversions._

object TopStock {

  def main(args: Array[String]): Unit = {

    val symbol = List("AMD","AAPL","AMZN")

    var price = new mutable.HashMap[String, Int]()

    price.put("AMD",23)

    price.put("AAPL",24)

    price.put("AMZN",25)

    val (topStock,topPrice) = symbol.map(ticker => (ticker, price.get(ticker))).maxBy(stockPrice => stockPrice._2)

    println(s"top stock is  $topStock is price  $$$topPrice")

    var ints = new util.ArrayList[Int]()

    for(i <- 1 to 1000){
      ints.add(i)
    }

    val max = findMax(ints)

    println(s"intput max is $max")

  }

  def findMax(inputTemperature:util.ArrayList[Int]) = {
    var highTemperature = Integer.MIN_VALUE

    for(temperature <- inputTemperature){
      highTemperature = Math.max(highTemperature,temperature)
    }

    highTemperature
  }


}
