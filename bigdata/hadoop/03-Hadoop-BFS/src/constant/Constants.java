package constant;

public interface Constants {
//	本地原始數據路徑
//	public static final String LOCAL_RAW_DATA_PATH_STRING ="/root/eclipse-workspace/05-Hadoop-BFS/src-data/mediumEWG.txt";
//	public static final String LOCAL_RAW_DATA_PATH_STRING ="/root/eclipse-workspace/05-Hadoop-BFS/src-data/input-data.txt";
	public static final String LOCAL_RAW_DATA_PATH_STRING ="/root/eclipse-workspace//05-Hadoop-BFS/src-data/10000EWG.txt";
	
	
//	hdfs上沒有預處理的原始數據路徑
	public static final String HDFS_RAW_DATA_PATH_STRING ="hdfs://hadoop-0:9000/BFS/input-src-data.txt";
//	public static final String HDFS_RAW_DATA_PATH_STRING ="hdfs://hadoop-0:9000/test/input-src-data.txt";
	
//	預處理結束後的數據存儲路徑
	public static final String HDFS_PREPARED_DATA_PATH_STRING="hdfs://hadoop-0:9000/BFS/prepared-data/";
//	public static final String HDFS_PREPARED_DATA_PATH_STRING="hdfs://hadoop-0:9000/test/prepared-data";
	
//  当前是bfs中第几次mapreduce 
	public static final String BFS_MAPREDUCE_TIMES_STRING="bfsMrTimes";
	
//	是否需要進一步mapreduce計數器的名字
	public static final String NEED_MORE_MR_FLAG_STRING="IsNeedMoreMR";
	
	//中间数据根目录
	public static final String HDFS_BFS_OUT_ROOT_DIR="hdfs://hadoop-0:9000/BFS/step-";
}
