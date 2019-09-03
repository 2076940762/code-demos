package newIO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class FileChannelDemo {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		String strSrc = "������ֻ��������������籯���ȡ����б�ȴ�����ģ�ȴ���������ױ䡣��ɽ��������룬���������ղ�Թ�����籡�ҽ����ɣ�������֦����Ը!";
		FileChannel fc = new FileOutputStream("./temp").getChannel();
		// strSrc.getBytes() Encodes this String into a sequence of bytes using
		// theplatform's default charset
		// ByteBuffer.wrap() Wraps a byte array into a buffer.
		fc.write(ByteBuffer.wrap(strSrc.getBytes()));
		fc.close();
		
		// ׷��
		String str2 = "�������У��Գ�ͤ�������Ъ���������������������������۴߷���ִ���࿴���ۣ���������ҭ����ȥȥ��ǧ���̲���ĺ�������������������Թ�����𣬸��ǿ���������ڡ��������Ѻδ�����������������¡���ȥ���꣬Ӧ�������þ����衣������ǧ�ַ��飬�������˵��";
		fc = new FileOutputStream("./temp").getChannel();
		fc.position(fc.size());// Returns the current size of this channel's file.
		fc.write(ByteBuffer.wrap(str2.getBytes()));
		fc.close();

		// ��
		ByteBuffer dst = ByteBuffer.allocate(1024);
		fc = new FileInputStream("./temp").getChannel();
		fc.read(dst);
		//Flips this buffer. The limit is set to the current position and thenthe position is set to zero. If the mark is defined then it isdiscarded. 
		dst.flip();//
		Charset cs = Charset.defaultCharset();// ���ر��ز���ϵͳ���ַ���
		// Convenience method that decodes bytes in this charset into Unicodecharacters.
		CharBuffer cb = cs.decode(dst);
		System.out.println(cb.toString());
		fc.close();

	}

}
