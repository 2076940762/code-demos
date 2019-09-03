package bfs;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class BfsJob {
	
	public static void main(String[] args) throws Exception {
		long beginTime = System.currentTimeMillis();
		
		//预处理数据
//		new FormateData().preparedData();
		
		 Logger.getLogger(BfsJob.class).setLevel(Level.ERROR);
		
		Configuration configuration = new Configuration();

		// 获取hdfs客户端
		configuration.set("fs.defaultFS", "hdfs://hadoop-0:9000/");
		FileSystem fileSystem = FileSystem.get(new URI("hdfs://hadoop-0:9000/"), configuration, "root");

		int i = 1;
		while (true) {
			// 将循环次数传给mapper
			configuration.setInt(constant.Constants.BFS_MAPREDUCE_TIMES_STRING, i);
			
			//设置输出的key,value分隔符为tab
			configuration.set("mapred.textoutputformat.separator", "\t"); 
			
			//本地调试
//			configuration.set("mapred.job.tracker", "local");
			
			Job job = Job.getInstance(configuration);

			// job
			job.setJarByClass(BfsJob.class);
			job.setInputFormatClass(KeyValueTextInputFormat.class);

			// mapper reducer
			job.setMapperClass(BfsMapper.class);
			job.setReducerClass(BfsReducer.class);

			// map 輸出
			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(Text.class);

			// reduce 輸出
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(Text.class);

			// 指定要处理的输入数据存放路径
			if (i == 1) {
				FileInputFormat.setInputPaths(job, new Path(constant.Constants.HDFS_PREPARED_DATA_PATH_STRING));
			} else {
				FileInputFormat.setInputPaths(job, new Path(constant.Constants.HDFS_BFS_OUT_ROOT_DIR + (i - 1)));
			}

			// 指定处理结果的输出数据存放路径
			Path path = new Path(constant.Constants.HDFS_BFS_OUT_ROOT_DIR + i);
			if (fileSystem.exists(path)) {
				fileSystem.delete(path);
			}
			FileOutputFormat.setOutputPath(job, path);

			// 将job提交给集群运行
			job.waitForCompletion(true);

			// 结束条件
			Counter counter = job.getCounters().findCounter("BFS", constant.Constants.NEED_MORE_MR_FLAG_STRING);
			if (counter.getValue() == 0L) {
				System.out.println("success\n"+"結果在："+"hdfs://hadoop-0:9000/dijkstra/step-" + i);
				break;
			}
			i++;
			System.out.println("第"+i+"次迭代完成");
		}
		
		System.out.println("共耗時" +(System.currentTimeMillis()-beginTime) +"ms" );
	}

}
