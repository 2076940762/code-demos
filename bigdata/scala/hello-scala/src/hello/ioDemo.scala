package hello

import java.io.PrintWriter
import scala.io.Source
import scala.io.BufferedSource

object ioDemo {
  
  def main(args: Array[String]): Unit = {
    //输入整数
    println("请输入一个整数");
    var i=readInt();
    println("i = "+i);
    
    println("请输入一个浮点数");
    var f=readFloat();
    println("f="+f);
    
    println("请输入字符串");
    val str= readLine();
    println("str = "+str);
    
    printf("i=%d,f=%f,str=%s\n", i,f,str);
    
    //s差值字符串
    println(s"i=$i;f=$f;str=$str");
    
    //f字符串
    println(f"i=$i%5d ; f=$f%2.1f ;str=$str%s ");
    
    
    println("===========================");
    
    /*
     * 写文件
     */
    val file1=new PrintWriter("test.txt");
    file1.println(f"i=$i%5d ; f=$f%2.1f ;str=$str%s ");
    file1.close();
    
    /*
     * 读文件
     */
    var in=Source.fromFile("test.txt");
    var lines=in.getLines();
    for(line <- lines){
      println(line);
    }
  }
  

}