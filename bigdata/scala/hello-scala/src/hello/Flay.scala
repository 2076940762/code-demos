package hello

trait Flay {

  def fly; //抽象方法

}

trait Eat {
  val times: Int;

  def eat;
}

trait Run {

  //具体方法
  def run {
    println("I am running !!!");
  }

}

class Animal extends Eat {
  val times: Int = 10;

  def eat() {
    println(s"I eat $times everyday!!");
  }

}

class Bird extends Animal with Flay with Run {

  def fly() {
    println("i can fly!!!!~~~~");
  }

}

object TestIng{
  
  def main(args: Array[String]): Unit = {
    var b= new Bird;
    b.fly();
    b.eat();
    b.run;
  }
  
}















