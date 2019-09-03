package newIO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class convertCharsetDemo {
	public static void main(String[] args) throws IOException {
		String str = "�������У��Գ�ͤ�������Ъ���������������������������۴߷���ִ���࿴���ۣ���������ҭ����ȥȥ��ǧ���̲���ĺ�������������������Թ�����𣬸��ǿ���������ڡ��������Ѻδ�����������������¡���ȥ���꣬Ӧ�������þ����衣������ǧ�ַ��飬�������˵��\n";

		ByteBuffer buffer = ByteBuffer.wrap(str.getBytes("utf-8"));
		// Returns a charset object for the named charset.
		Charset csUtf8 = Charset.forName("utf-8");
		CharBuffer charbuf = csUtf8.decode(buffer);
		System.out.println(charbuf.toString());
		
		FileOutputStream fout =new FileOutputStream("./temp");
		//unicode
		fout.write(charbuf.toString().getBytes());
		//utf-8
		fout.write(buffer.array());
		fout.write(new String(buffer.array()).getBytes());
		fout.close();

	}

}
