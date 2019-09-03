package word.conunt;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MyReducer extends Reducer<Text, LongWritable, Text, LongWritable> {

	@Override
	protected void reduce(Text key, Iterable<LongWritable> values,
			Reducer<Text, LongWritable, Text, LongWritable>.Context context) throws IOException, InterruptedException {
		//暫存一個key的部分和
		long sum =0;
		
		//求和
		for (LongWritable longWritable : values) {
			sum+=longWritable.get();
		}
		
		//結果寫入
		context.write(key, new LongWritable(sum));
	}

}
