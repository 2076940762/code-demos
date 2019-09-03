package hello.hdfs;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.junit.Test;

public class hello {
	
	
	// 构造一个配置参数对象，设置一个参数：我们要访问的hdfs的URI
	// 从而FileSystem.get()方法就知道应该是去构造一个访问hdfs文件系统的客户端，以及hdfs的访问地址
	// new Configuration();的时候，它就会去加载jar包中的hdfs-default.xml
	// 然后再加载classpath下的hdfs-site.xml
	//Configuration conf = new Configuration();
	//conf.set("fs.defaultFS", "hdfs://hdp-node01:9000");
	/*
	    * 参数优先级： 1、客户端代码中设置的值 2、classpath下的用户自定义配置文件 3、然后是服务器的默认配置
	*/
    //
	// 获取一个hdfs的访问客户端，根据参数，这个实例应该是DistributedFileSystem的实例
	// fs = FileSystem.get(conf);
    //
	// 如果这样去获取，那conf里面就可以不要配"fs.defaultFS"参数，而且，这个客户端的身份标识已经是hadoop用户
	//fs = FileSystem.get(new URI("hdfs://hdp-node01:9000"), conf, "hadoop");
	
	
//	
//	@Before
//	public void init() throws Exception{
//		
//		//读取classpath下的xxx-site.xml 配置文件，并解析其内容，封装到conf对象中
//		Configuration conf = new Configuration();
//		
//		//也可以在代码中对conf中的配置信息进行手动设置，会覆盖掉配置文件中的读取的值
//		conf.set("fs.defaultFS", "hdfs://weekend110:9000/");
//		
//		//根据配置信息，去获取一个具体文件系统的客户端操作实例对象
//		fs = FileSystem.get(new URI("hdfs://weekend110:9000/"),conf,"hadoop");
//	}

	/**
	 * 查看hdfs中目前有那些文件
	 * 
	 * @throws Exception
	 */
	@Test
	public void tree() throws Exception {

		// 读取classpath下的xxx-site.xml 配置文件，并解析其内容，封装到conf对象中
		Configuration configuration = new Configuration();

		// 也可以在代码中对conf中的配置信息进行手动设置，会覆盖掉配置文件中的读取的值
		configuration.set("fs.defaultFS", "hdfs://hadoop-0:9000/");

		// 根据配置信息，去获取一个具体文件系统的客户端操作实例对象
		FileSystem fileSystem = FileSystem.get(new URI("hdfs://hadoop-0:9000/"), configuration, "hadoop");

		System.out.println(fileSystem);


		RemoteIterator<LocatedFileStatus> fileStatus = fileSystem.listFiles(new Path("hdfs://hadoop-0:9000/"),
				true);
		System.out.println(fileStatus);
		
		while (fileStatus.hasNext()) {
			 LocatedFileStatus status = fileStatus.next();
			 System.out.println(status.getPath().getName());			
		}
	}
	
	/**
	 * 上传文件
	 * @throws Exception 
	 */
	@Test
	public void upload() throws  Exception {
		Configuration configuration = new Configuration();
		configuration.set("fs.defaultFS", "hdfs://hadoop-0:9000/");
		
		FileSystem fileSystem = FileSystem.get(new URI("hdfs://hadoop-0:9000/"), configuration, "root");
		
		Path path = new Path("hdfs://hadoop-0:9000/haddop-src");
		FSDataOutputStream outputStream = fileSystem.create(path, true);
		
		FileInputStream inputStream = new FileInputStream("/home/hadoop/software/hadoop-2.9.2-src.tar.gz");
		
		IOUtils.copy(inputStream, outputStream);
		
	}
	
	/**
	 * 查看文件信息
	 * @throws IOException 
	 * @throws IllegalArgumentException 
	 * @throws FileNotFoundException 
	 * @throws URISyntaxException 
	 * @throws InterruptedException 
	 * 
	 */
	@Test
	public void listFiles() throws Exception {
		Configuration configuration = new Configuration();
		configuration.set("fs.defaultFS", "hdfs://hadoop-0:9000/");
		
		FileSystem fs = FileSystem.get(new URI("hdfs://hadoop-0:9000/"), configuration, "root");

		// listFiles列出的是文件信息，而且提供递归遍历
		RemoteIterator<LocatedFileStatus> files = fs.listFiles(new Path("/"), true);
		
		while(files.hasNext()){
			
			LocatedFileStatus file = files.next();
			Path filePath = file.getPath();
			String fileName = filePath.getName();
			System.out.println(fileName);
			
		}
		
		System.out.println("---------------------------------");
		
		//listStatus 可以列出文件和文件夹的信息，但是不提供自带的递归遍历
		FileStatus[] listStatus = fs.listStatus(new Path("/"));
		for(FileStatus status: listStatus){
			
			String name = status.getPath().getName();
			System.out.println(name + (status.isDirectory()?" is dir":" is file"));
			
		}
		
	}
	
	/**
	 * 下載文件
	 * @throws Exception 
	 */
	@Test
	public void download() throws  Exception {
		Configuration configuration = new Configuration();
		configuration.set("fs.defaultFS", "hdfs://hadoop-0:9000/");
		
		FileSystem fileSystem = FileSystem.get(new URI("hdfs://hadoop-0:9000/"), configuration, "root");
		
		fileSystem.copyToLocalFile(new Path("hdfs://hadoop-0:9000/haddop-src"), new Path("/root/software/hadoop-2.9.2-src.tar.gz"));
	}
	
	
	/**
	 * 创建文件夹
	 * @throws Exception 
	 */
	@Test
	public void mkdir() throws  Exception {
		Configuration configuration = new Configuration();
		configuration.set("fs.defaultFS", "hdfs://hadoop-0:9000/");
		
		FileSystem fileSystem = FileSystem.get(new URI("hdfs://hadoop-0:9000/"), configuration, "root");
		
		fileSystem.mkdirs(new Path("/mydata/"));
		
	}
	
}
