package hello

import java.io.FileNotFoundException

object Exceptions {
  
  def main(args: Array[String]): Unit = {
        try {
          
        } catch {
            case t:FileNotFoundException=>t.printStackTrace();
            case t:ClassNotFoundException=>{
              
            }
            case t: Throwable => t.printStackTrace() ;
        }  finally {
          
        }
  }
  
}