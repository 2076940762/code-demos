package preprocessing;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

//map函數的輸入key值爲文件偏移量
public class FormaterMapper extends Mapper<LongWritable, Text, Text, Text> {

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		//輸入數據格式 9912 9980 0.01251 節點一A，節點二B，距離
		String[] arr = value.toString().split("\\s+");
		
		context.write(new Text(arr[0]), new Text("("+arr[1]+","+arr[2]+")"));//A (B,距離)
		context.write(new Text(arr[1]), new Text("("+arr[0]+","+arr[2]+")"));//B （A，距離）
	}

}
