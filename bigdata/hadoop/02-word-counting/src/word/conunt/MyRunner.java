package word.conunt;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * 用来描述一个特定的作业
 * 比如，该作业使用哪个类作为逻辑处理中的map，哪个作为reduce
 * 还可以指定该作业要处理的数据所在的路径
 * 还可以指定改作业输出的结果放到哪个路径
 * @author root
 */
public class MyRunner {
	
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		
		Job wcjob = Job.getInstance(conf);
		
		//设置整个job所用的那些类在哪个jar包
		wcjob.setJarByClass(MyRunner.class);
		
		
		//本job使用的mapper和reducer的类
		wcjob.setMapperClass(MyMapper.class);
		wcjob.setReducerClass(MyReducer.class);
		
		
		//指定reduce的输出数据kv类型
		wcjob.setOutputKeyClass(Text.class);
		wcjob.setOutputValueClass(LongWritable.class);
		
		//指定mapper的输出数据kv类型
		wcjob.setMapOutputKeyClass(Text.class);
		wcjob.setMapOutputValueClass(LongWritable.class);
		
		//指定要处理的输入数据存放路径
		FileInputFormat.setInputPaths(wcjob, new Path("hdfs://hadoop-0:9000/word-count"));
		
		//指定处理结果的输出数据存放路径
		FileOutputFormat.setOutputPath(wcjob, new Path("hdfs://hadoop-0:9000/word-count-out"));
		
		//将job提交给集群运行 
		wcjob.waitForCompletion(true);
		
	}
	

}
