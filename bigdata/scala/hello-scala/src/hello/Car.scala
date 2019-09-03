package hello

import java.text.Normalizer.Form

/**
 * case 类会自动生成伴生单例对象并生成apply和unapply方法
 */
case class Car(var brand: String, var price: Double);

object TestCar {

  def main(args: Array[String]): Unit = {
    for (car <- List(Car("AAA", 120), Car("BBB", 134), Car("ABC", 189))) {
      car match {
        case Car("AAA", 120) => println("hello AAA");
        case Car("BBB", 134) => println("hello BBB");
        case Car(b,p)=>println(s"brand : $b,price : $p");  //unapply
      }
    }
  }
}