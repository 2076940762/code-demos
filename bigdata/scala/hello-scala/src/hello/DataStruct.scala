package hello

import java.util.Arrays

object DataStruct {

  def main(args: Array[String]): Unit = {
    arrayOperations();
    tupleOpes();
    collectionsOpe();
  }

  /*
   * 数组操作
   */
  def arrayOperations() {
    val arr1 = new Array[Int](10); //长度为10 的整型数组

    for (i <- 0 to 9) {
      arr1(i) = i; //数组元素赋值
    }

    println(Arrays.toString(arr1));

    val strarr = Array("hadoop", "big data", "spark");

    //多为数组
    Array.ofDim[Int](3, 4) //3行4列的二维数组

    var arr2 = Array.ofDim[Int](4, 5);
    for (i <- 0 to 3; j <- 0 to 4) {
      arr2(i)(j) = i * j;
    }

    for (i <- 0 to 3; j <- 0 to 4) {
      printf("%5s",arr2(i)(j));
      if(j==4){
        println();
      }
    }
  }

  /*
   * tuple
   */
  def tupleOpes():Unit={
    val t=("hadoop",123,3.1415926,true);
    println(t._1);
    println(t._2);
    println(t._3);
    println(t._4);
  }
  
  /*
   * 集合操作
   */
  def collectionsOpe():Unit={
    //list
    val list1=List("hadoop","spark","scala");
    val list2="tomcat"::list1;
    println(list2);
    
    //vector
    val vector1=Vector(1,2,3,4,5,6);
    val vector2=199 +:vector1;
    val vector3=vector2:+899;
    println(vector3);
    
    //range
    val r1=1 to 5;
    val r2=1 until 5;
    println(s"r1=$r1");
    println(s"r2=$r2");
    
    //set
    var set1= Set("scala","spark");  //默认创建的是不可变集合
    set1+="could computing";//虽然集合是不可变集合，但set1是可变的，此处生成了一个新的set实例，set1指向新的实例
    println(s"set1=$set1");
    
    val set2 = scala.collection.mutable.Set(1,2,3,4,5);//可变集合
    set2+=99;
//    set2=set1; set2为val，不能改变其指向；但set2指向的是一个可变集合 
    println(s"set2=$set2");
    
//map
    var m1=Map("npu"->"西工大","cs"->"计算机");
    println(s"m1=$m1");
    if(m1.contains("npu") ){
    	println(m1("npu"));
    }
    
  }
  
}