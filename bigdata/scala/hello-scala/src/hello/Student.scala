package hello


class Student( d: Int, val name: String, age:Int) {
/*
 * 有var,val修饰的主构造器参数会自动变为类的成员变量
 * var 可变
 * val 不可变
 */
  
  //默认为public的
   private  var m_age:Int=age; 
   
   def Age=m_age;       //getter
   def Age_=(age:Int){   //setter
     this.m_age=age;
   }
   
   private var id:Int=d;
   
   //id的get和set方法
   def Id=id;
    def Id_=(d:Int):Unit={
     id=d;
   }

}