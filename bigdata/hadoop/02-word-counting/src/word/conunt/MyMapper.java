package word.conunt;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

//4个泛型中，前两个是指定mapper输入数据的类型，KEYIN是输入的key的类型，VALUEIN是输入的value的类型
//map 和 reduce 的数据输入输出都是以 key-value对的形式封装的
//默认情况下，框架传递给我们的mapper的输入数据中，key是要处理的文本中一行的起始偏移量，这一行的内容作为value
public class MyMapper extends Mapper<LongWritable, Text, Text, LongWritable> {

	//mapreduce框架每读一行数据就调用一次该方法
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, LongWritable>.Context context)
			throws IOException, InterruptedException {
		//将输入的一行内容转换为字符串
		String string = value.toString();
		
		//空格切分字符串
		String[] strings = StringUtils.split(string, " ");
		
		//遍历这个单词数组输出为kv形式  k：单词   v ： 1
		for(String word : strings) {
			context.write(new Text(word), new LongWritable(1));
		}
		
	}
	

}
