package bfs;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


//enum BFS{
//	IsNeedMoreMR
//}
/**
 * 输出： key为节点号如A，value该节点更新后的局部最短距离，以及所有邻接节点（B，距离），（C，223）....
 * @author root
 *
 */
public class BfsReducer extends Reducer<Text, Text, Text, Text> {
	
	/**
	 * 从当前节点key出发，向相邻节点扩展，同时更新距离
	 */
	@Override
	protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		
		Double minDisDouble=new Double(Double.MAX_VALUE);//最小距离
		Double dis =null; 
		String outValue="";
		Double originDis=null;//上次迭代后距离
		
		for (Text text : values) {
			 String[] splits = text.toString().split("\\s+");
			 
			 //獲取輸入的距離
			 if (splits[0].trim().equalsIgnoreCase("infinity")) {
				 dis=Double.MAX_VALUE;
			 }else {
				 dis= Double.parseDouble(splits[0].trim());//map输出的距离
			 }
			 
			 //邻居节点信息
			 if (splits.length>1) {
				for (int j = 1; j < splits.length; j++) {
					outValue+=(splits[j]+"\t");
				}
				originDis=dis;
			}
			 
			 //更新最小距離
			 if (minDisDouble>dis) {
				minDisDouble=dis;
			}
		}//for
		
		//有更小的距离，还需要迭代
		if (originDis!=null && originDis>minDisDouble) {
			context.getCounter("BFS", constant.Constants.NEED_MORE_MR_FLAG_STRING).increment(1L);
		}
		
		//輸出 
		if (minDisDouble.equals(Double.MAX_VALUE)) {
			context.write(key, new Text("infinity"+"\t"+outValue));
		}else {
			context.write(key, new Text(minDisDouble+"\t"+outValue));
		}
	}

}
