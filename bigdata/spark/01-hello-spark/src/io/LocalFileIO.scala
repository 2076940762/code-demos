package io

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object LocalFileIO {
  
  def main(args:Array[String]):Unit={
    
    val conf=new SparkConf().set("spark.driver.allowMultipleContexts","true").setMaster("local[*]").
    setAppName("LocalFileIO");
    val sc=new SparkContext(conf)
    
    val rdd=sc.textFile("file:///root/eclipse-workspace/06-hello-spark/src-data/word-count-data.txt", 5).flatMap(line=>line.split("\\s+")).map(word=>(word,1)).reduceByKey(_+_).sortBy(kv=>kv._2, false)                           
    
    println(rdd.count())
    rdd.collect();
    
    rdd.saveAsTextFile("/root/RDD/bfsrdd/");
    
    
    //读
    val rdd2 =sc.textFile("file:////root/RDD/bfsrdd/", 2)
    rdd2.foreach(println)
  }
  
}