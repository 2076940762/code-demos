package preprocessing;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


//输入 key :节点编号A，value:(B,123) 表示B节点与A相邻，距离为123
//输出：key :节点编号A，value:(B,123) （C，1） （D，4）.... 由A节点出发的所有相邻节点编号及距离
public class FormaterReducer extends Reducer<Text, Text, Text, Text> {

	@Override
	protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
//		拼接輸出字符串
		StringBuilder buffer = new StringBuilder("");
		for (Text text : values) {
			buffer.append(text.toString()+"\t");
		}

//		輸出結果
		context.write(key, new Text(buffer.toString()));
	}

}
