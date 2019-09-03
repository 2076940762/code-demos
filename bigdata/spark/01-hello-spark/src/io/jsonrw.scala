package io

import scala.util.parsing.json.JSON

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object jsonrw {
  
  def main(args:Array[String]):Unit={
    
    val conf=new SparkConf().set("spark.driver.allowMultipleContexts","true").setMaster("local[*]").
    setAppName("LocalFileIO");
    val sc=new SparkContext(conf)
    
    val rdd = sc.textFile("file:////usr/local/spark/spark-2.4.3-bin-without-hadoop-scala-2.12/examples/src/main/resources/employees.json", 1)
    rdd.foreach(println)

    val res=rdd.map(str=>{JSON.parseFull(str)})
    res.foreach(
        json=>json  match {
          case Some(map: Map[String, Any]) => println(map)
          case None => println("failed")
          case other =>println("unknown")
    }
      )


  }
  
}