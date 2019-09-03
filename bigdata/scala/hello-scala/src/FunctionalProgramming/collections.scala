package FunctionalProgramming

object collections {

  def main(args: Array[String]): Unit = {
    //list遍历
    val list = List(1, 2, 3, 4, 5, 5, 6, 7);
    list.foreach((a: Int) => println(a));

    //map的遍历
    val map = Map(1 -> "壹", 2 -> "贰", 3 -> "叁", 4 -> "肆", 5 -> "五", 6 -> "陆");
    map.foreach((m) => { println(m._1 + ":" + m._2) })
    map.foreach(x => x match {
      case (k, v) => println(k + ":#:" + v);
    })
    map.foreach( {case (k,v)=>println(k+"~~~"+v) }  )
    
    println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%")
    //map 方法
    val list2=List("hadoop","mapreduce","spark");
    var list3= list2.map(s=>s.toUpperCase())
    list2.foreach( x =>{println(x)} )
    list3.foreach( x =>{println(x)} )
    
    //flatmap
    val list4=list2.flatMap(x=>x.toList)
    list4.foreach(s=>print(s) )
    println()
    
    //filter
     var list5=list2.filter(   x=>{x.contains("d")}        )
     list5.foreach(s=>println(s) )
    
     //reduce
     val res1 = list.reduce(_+_)//累加
     println(res1)
     val res2 =list.reduceRight(_-_)
     println(res2)
     val res3=list.reduce(_-_)
     println(res3)
     
     //flod
     var res4=list.foldLeft(100)(_-_)
     println(res4)
     
     var res5=list.foldRight(100)(_-_)
     println(res5)
  }

}