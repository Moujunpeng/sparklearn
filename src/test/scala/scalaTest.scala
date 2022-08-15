package workspare.sparktesst.src.test.scala

import org.scalatest.funsuite.AnyFunSuite

class scalaTest extends AnyFunSuite{

  test("greet") {

    for(i <- 1 to 100){
      print(s"intput $i,")
    }
    println("print end ")

  }

}
