package IODemo;

import java.io.File;
import java.io.RandomAccessFile;

public class RandomAccessFileDemo {
	public static void main(String[] args) throws Exception {
		File f= new File("./temp");
		if (f.exists()) {
			f.delete();
		}
	
		String strSrc = "人生若只如初见，何事西风悲画扇。等闲变却故人心，却道故人心易变。骊山语罢清宵半，泪雨零铃终不怨。何如薄幸锦衣郎，比翼连枝当日愿!";
		RandomAccessFile rf = new RandomAccessFile(f, "rw");
		rf.write(strSrc.getBytes("utf-8"));

		rf.seek(0);
		byte[] buffer = new byte[1024];
		int len = rf.read(buffer);
		String str =new String(buffer, 0, len, "UTF-8");
		System.out.println(str);

		rf.close();
	}

}
