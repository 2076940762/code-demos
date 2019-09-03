package bfs;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * map输入key为节点号如A，value该节点目前得到的局部最短距离，以及所有邻接节点（B，距离），（C，223）....
 * 输出格式同输入格式
 */
public class BfsMapper extends Mapper<Text, Text, Text, Text> {

	@Override
	protected void map(Text key, Text value, Mapper<Text, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		//獲取當前是第幾次mapreduce
		int CONUTER = Integer.parseInt(	context.getConfiguration().get(constant.Constants.BFS_MAPREDUCE_TIMES_STRING));

		String outValue=null;		//输出value
		String currNodedistanceistance=null;		//当前已获取的局部最短距离
		String[] neighborNodes = value.toString().split("\\s+");	//距离+鄰居節點
		
		//第一次mapreduce 
		if (CONUTER == 1) {
			if (key.toString().trim().equals("0")|| key.toString().trim().equalsIgnoreCase("a")) {
				currNodedistanceistance="0";
			}else {
				currNodedistanceistance="infinity";
			}
			outValue=currNodedistanceistance+"\t"+value.toString();
		}
		else {
			outValue=value.toString();
			currNodedistanceistance=neighborNodes[0];
		}
		
		context.write(key, new Text(outValue));
		
		//尚未遍历到key节点
		if (currNodedistanceistance.trim().equalsIgnoreCase("infinity")) {
			return ;
		}
		
		/*
		 *跟新从该（key）节点出发，相邻节点的到元节点的距离
		 */
		int i=1;
		if (CONUTER == 1) {
			i=0;
		}
		for (;i<neighborNodes.length;i++) {
			String node = neighborNodes[i].trim();//(211,0.08438) 
			node=node.substring(1,node.length()-1);//211,0.08438
			String[] arr = node.split(",");
			
			//从当前节点key出发到node的距离，传给reduce 作比较；这里currNodedistanceistance肯定不是infinity
			String dis = Double.parseDouble(arr[1])+Double.parseDouble(currNodedistanceistance)+"";
			
			/*没办法获取node节点的相邻节点，先这样吧 */
			context.write(new Text(arr[0]), new Text(dis));
		}
	}

}
