package IODemo;

import java.io.File;
import java.io.RandomAccessFile;

public class RandomAccessFileDemo {
	public static void main(String[] args) throws Exception {
		File f= new File("./temp");
		if (f.exists()) {
			f.delete();
		}
	
		String strSrc = "������ֻ��������������籯���ȡ����б�ȴ�����ģ�ȴ���������ױ䡣��ɽ��������룬���������ղ�Թ�����籡�ҽ����ɣ�������֦����Ը!";
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
