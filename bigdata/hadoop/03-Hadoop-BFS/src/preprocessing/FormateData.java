package preprocessing;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.junit.Test;

public class FormateData {
	
	/**
	 * 上傳，預處理
	 * @throws Exception
	 */
	@Test
	public void preparedData() throws Exception {
		upload();
		transform();
	}
	
	/**
	 * 原始數據文件上傳hdfs
	 * @throws Exception 
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	@Test
	public void upload() throws Exception {
		Configuration configuration = new Configuration();
		configuration.set("fs.defaultFS", "hdfs://hadoop-0:9000/");
		
		//获取hdfs文件系统客户端对象
		FileSystem fileSystem = FileSystem.get(new URI("hdfs://hadoop-0:9000/"), configuration, "root");
		
		//创建原始数据存储路径，如果存在则删除
		Path path = new Path(new URI(constant.Constants.HDFS_RAW_DATA_PATH_STRING));
		if (fileSystem.exists(path)) {
			fileSystem.delete(path);
		}
		
		FSDataOutputStream outputStream = fileSystem.create(path);		
		FileInputStream inputStream = new FileInputStream(constant.Constants.LOCAL_RAW_DATA_PATH_STRING);		
		IOUtils.copy(inputStream, outputStream);
	}
	
	
	/**
	 * 轉換數據格式
	 * @throws Exception 
	 */
	@Test
	public void transform() throws Exception {
		Configuration configuration = new Configuration();
		
		// 获取hdfs客户端
		configuration.set("fs.defaultFS", "hdfs://hadoop-0:9000/");
		FileSystem fileSystem = FileSystem.get(new URI("hdfs://hadoop-0:9000/"), configuration, "root");
		
		//设置输出的key,value分隔符为tab
		configuration.set("mapred.textoutputformat.separator", "\t"); 
		Job job = Job.getInstance(configuration);
		
		//jar
		job.setJarByClass(FormateData.class);
		
		//mapper,reducer
		job.setMapperClass(FormaterMapper.class);
		job.setReducerClass(FormaterReducer.class);
		
		//map <key,value>
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		
		//reducer <k,v>
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		//輸入，輸出文件路徑
		Path path = new Path(constant.Constants.HDFS_PREPARED_DATA_PATH_STRING);
		if (fileSystem.exists(path)) {
			fileSystem.delete(path);
		}
		FileInputFormat.setInputPaths(job, new Path(constant.Constants.HDFS_RAW_DATA_PATH_STRING));
		FileOutputFormat.setOutputPath(job, path);
		
		//提交集羣運行
		job.waitForCompletion(true);
	}

}
