package hello
/**
 * 单例对象
 * 孤立对象
 * 单例对象的方法，相当于java中类的静态方法
 */
object HelloScala {

  var str:String="hello";
  
  //程序总入口
  def main(args: Array[String]): Unit = {
    println("hello");

    //var 表示s是可变的，val表示不可变，s类型为Student
    var s=new Student(11,"张三",10);
    println("id:"+s.Id+"\t"+"name:"+s.name+"\t"+"年龄"+s.Age);
    s.Age_=(110);
    s.Age=112;
    
    s.Id_=(111);
    println("id:"+s.Id+"\t"+"name:"+s.name+"\t"+"年龄"+s.Age);
  }
  
  
  
}