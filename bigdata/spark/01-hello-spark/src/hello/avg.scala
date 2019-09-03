package hello

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object avg {
  
  /**
   * 求圖書的平均銷量
   * ("hadoop",10)代表hadoop這本書某一天的銷量
   * 類型轉換("hadoop",10，1)  ("hadoop",20，1)
   * 平均銷量=總銷量（10+20+..）/總天數(1+1...)
   */
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().set("spark.driver.allowMultipleContexts","true").setMaster("local[*]").
    setAppName("average");
    val sc=new SparkContext(conf)
    
    val rdd1= sc.parallelize(Array(("hadoop",10),("scala",23),("hadoop",56),("spark",33),("scala",54),("hadoop",96),("scala",2),("spark",55),("hadoop",32),("spark",1110),("hadoop",36),("hadoop",1),("spark",1)), 2)
    val resrdd = rdd1.mapValues(x=>(x,1)).reduceByKey((x,y)=> (x._1+y._1,x._2+y._2) ).mapValues(v=>v._1/v._2)
    resrdd.collect();
    resrdd.foreach(println);
  }
  
}