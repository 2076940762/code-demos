package IODemo;

import java.io.IOException;
import java.io.StringReader;

public class StringReaderDemo {
	public static void main(String[] args) throws IOException {
		String strSrc ="������ֻ��������������籯���ȡ����б�ȴ�����ģ�ȴ���������ױ䡣��ɽ��������룬���������ղ�Թ�����籡�ҽ����ɣ�������֦����Ը!";
		System.out.println("len:"+strSrc.length());
		StringReader reader = new StringReader(strSrc);
		int data=0;
		while((data=reader.read()) != -1) {
			System.out.print((char)data);
			if (data == '��') {
				System.out.println();
			}
		}
		reader.close();
	}

}
