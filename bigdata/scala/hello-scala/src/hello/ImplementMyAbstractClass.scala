package hello

class ImplementMyAbstractClass extends MyAbstractClass {
  var a: Int = 123;
  val b: Boolean = false;

  //实现抽象方法override关键字可选
  override def f1(): Unit = {
    println("f1")
  }

//  重写抽象方法
  def f2(): Unit = {
    println("f2")
  }

  //  覆盖已经实现的方法必须有override关键字
  override def hello() { //非抽象方法
    println("hello scala");
  }

}

object ImplementMyAbstractClass {

  def main(args: Array[String]): Unit = {

  }

}









