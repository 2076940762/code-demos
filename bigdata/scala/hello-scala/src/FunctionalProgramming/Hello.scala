package FunctionalProgramming

object Hello {

  /*
   *
   */
  def main(args: Array[String]): Unit = {

    println(add(1, 2))
    println(sub(123,23));

    val mutil:(Int,Int)=>Int={
      (a,b)=>{
        a*b;
      }
    }
    println(mutil(9,8));

    val myMod=(a:Int,b:Int)=>{
      a%b;
    }
    println(myMod(100,3));
    
    //等价于(a:Int,b:Int)=>Int={(a,b)=>{a+b}}
    //(a:Int,b:Int)=>{a+b}
    val add2=(_:Int)+(_:Int);
    println(add2(123,321));

    
    println("高阶函数");
    
    /**
     * 高阶函数
     * f是一个输入参数为int，输出为int的函数
     */
    val sum=(a:Int,b:Int,f:(Int)=>Int)=>{
      var res:Int=0;
      for(i <- a to b){
        res=res+f(i);
      }
      res;
    }
    
    //1+2+3+4+....+100
    println(sum(1,100,(a)=>a));
    println(sum(1,100,(a)=>a*a));
    
  }

  def add(a: Int, b: Int): Int = {
    a + b;
  }
  
  /**
   * sub：函数名
   * (Int,Int)=>Int：函数类型
   *函数体 
   {
    (a,b)=>{
      a-b;
     }
   }
   */
  def sub :(Int,Int)=>Int={
    (a,b)=>{
      a-b;
    }
  }
  
}