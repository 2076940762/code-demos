package IODemo;

import java.io.CharArrayReader;
import java.io.IOException;

public class charArrReaderDemo {
	public static void main(String[] args) throws IOException {
		char [] buf="������ֻ��������������籯���ȡ����б�ȴ�����ģ�ȴ���������ױ䡣��ɽ��������룬���������ղ�Թ�����籡�ҽ����ɣ�������֦����Ը!".toCharArray();
		CharArrayReader reader = new CharArrayReader(buf);
		int data =-1;
		while((data = reader.read())!= -1) {
			System.err.print((char)data);
		}
		reader.close();
	}

}
