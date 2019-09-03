package hello

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

/**
 * 在spark-shell中能够正常运行
 * 使用jdk1.8时Lambda会报错，解决办法
 * 1.myConfiguration.setMaster("local[*]")  
 * 或
 * 2.myConfiguration.setJars( Array[String]("/root/"))
 * 打包要用到sbt
 */
object WordsCounting {
  
  
  //程序入口
  def main(args: Array[String]): Unit = {
    val myConfiguration =new SparkConf();
    myConfiguration.set("spark.driver.allowMultipleContexts","true");
//  myConfiguration.setMaster("spark://hadoop-0:7077")
    myConfiguration.setMaster("local[*]")
    myConfiguration.setAppName("wordcount")
    val sparkContext=new SparkContext(myConfiguration);
    
    
    //lines 爲一個RDD,元素爲文本文件中所有行
    val lines= sparkContext.textFile("file:///root/eclipse-workspace/06-hello-spark/src-data/word-count-data.txt", 4);
    val words=lines.flatMap(str=>str.split("\\s+"))//将每一行以空格切分为单词数组，再压扁生成一个新的RDD,其元素为文本中的所有单词
    val word_one= words.map((str:String)=>{(str,1)})//将每一个元素转换为k-v，k为str,v为1
    //val wordconuts= sparkContext.parallelize( word_one.reduceByKey(_+_).sortBy(kv=>kv._2, false).top(10)  ).sortBy(kv=>kv._2, false, 1)//先将key相同的KV合并（K,(V1,V2,V3)）,然后计算V1+V2+V3....
    
    val wordconuts=word_one.reduceByKey(_+_)
    wordconuts.collect()//将结果从其他机器上拉回
    wordconuts.foreach( KV=>{println(KV)} )


  }
  
}