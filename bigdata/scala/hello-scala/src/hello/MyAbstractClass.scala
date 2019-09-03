package hello

abstract class MyAbstractClass {
  var a: Int; //抽象字段
  val b: Boolean; //抽象字段

  def f1() //抽象方法

  def f2() //抽象方法

  def hello() { //非抽象方法
    println("hello");
  }
}