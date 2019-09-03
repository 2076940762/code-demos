package clustering

import org.apache.spark.mllib.clustering.{KMeans, KMeansModel}
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.log4j.{Level, Logger}

/**
 * 使用k-means方法聚类
 */
object KmeansClustering {
  
  def main(args: Array[String]): Unit = {
  
    val conf=new SparkConf().set("spark.driver.allowMultipleContexts","true").setMaster("local[*]").
    setAppName("k-meams-clustering");
    val sc=new SparkContext(conf)
    
    Logger.getRootLogger.setLevel(Level.ERROR)//减少日志输出
    
    // Load and parse the data
    val data = sc.textFile("hdfs://hadoop-0:9000/root/data/Wholesale_customers_data.csv")
    val parsedData = data.map(s => Vectors.dense(s.split(",").map(_.toDouble))).cache()//Creates a dense vector from a double array.

  // def train(data: RDD[Vector], k: Int, maxIterations: Int, initializationMode: String, seed: Long): KMeansModel
  // Permalink
  //Trains a k-means model using the given set of parameters.
  //
  //data:Training points as an RDD of Vector types.
  //
  //k:Number of clusters to create.
  //
  //maxIterations:Maximum number of iterations allowed.
  //
  //initializationMode:The initialization algorithm. This can either be "random" or "k-means||". (default: "k-means||")
  //
  //seed:Random seed for cluster initialization. Default is to generate seed based on system time.

  // Cluster the data into two classes using KMeans
  val model = KMeans.train(parsedData, 6, 10000,"k-means||")//Trains a k-means model using the given set of parameters.
  
  //保存聚类中心    
//  model.clusterCenters.foreach(center => { println("Clustering Center:"+center) })
//  model.clusterCenters.zipWithIndex.foreach(center => { println("Clustering Center:"+center) })
  sc.parallelize(model.clusterCenters.zipWithIndex, 1).saveAsTextFile("hdfs://hadoop-0:9000/root/clustering/centers")
  
  //保存聚类结果
//   parsedData.collect().foreach(
//   sample => {
//     val predictedCluster = model.predict(sample)
//     println(sample.toString + " belongs to cluster " + predictedCluster)
//   })
  sc.parallelize(parsedData.collect(), 1).map(sample=>{(model.predict(sample),sample)})
  .saveAsTextFile("hdfs://hadoop-0:9000/root/clustering/res")
  
  // Evaluate clustering by computing Within Set Sum of Squared Errors
  val WSSSE = model.computeCost(parsedData)
  println(s"Within Set Sum of Squared Errors = $WSSSE")
  
  // Save and load model
  model.save(sc, "hdfs://hadoop-0:9000/root/clustering/model")
  val sameModel = KMeansModel.load(sc, "hdfs://hadoop-0:9000/root/clustering/model")
  }
  
}






