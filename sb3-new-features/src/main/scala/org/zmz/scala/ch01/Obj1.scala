package org.zmz.scala.ch01

object Obj1 {
  def main(args: Array[String]): Unit = {
    Array(1, 2, 3, 4, 5, 6).map(_ * 2).foreach(println)
  }

}

trait Person {
  def sayHello(name: String)
}

trait Teacher extends Person {
  override def sayHello(name: String): Unit = {
    println("跟老师说 sayHello")
  }
}

trait Student extends Person with Teacher {

}