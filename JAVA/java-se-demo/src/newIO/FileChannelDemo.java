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
		String strSrc = "人生若只如初见，何事西风悲画扇。等闲变却故人心，却道故人心易变。骊山语罢清宵半，泪雨零铃终不怨。何如薄幸锦衣郎，比翼连枝当日愿!";
		FileChannel fc = new FileOutputStream("./temp").getChannel();
		// strSrc.getBytes() Encodes this String into a sequence of bytes using
		// theplatform's default charset
		// ByteBuffer.wrap() Wraps a byte array into a buffer.
		fc.write(ByteBuffer.wrap(strSrc.getBytes()));
		fc.close();
		
		// 追加
		String str2 = "寒蝉凄切，对长亭晚，骤雨初歇。都门帐饮无绪，留恋处，兰舟催发。执手相看泪眼，竟无语凝噎。念去去，千里烟波，暮霭沉沉楚天阔。多情自古伤离别，更那堪冷落清秋节。今宵酒醒何处？杨柳岸，晓风残月。此去经年，应是良辰好景虚设。便纵有千种风情，更与何人说！";
		fc = new FileOutputStream("./temp").getChannel();
		fc.position(fc.size());// Returns the current size of this channel's file.
		fc.write(ByteBuffer.wrap(str2.getBytes()));
		fc.close();

		// 读
		ByteBuffer dst = ByteBuffer.allocate(1024);
		fc = new FileInputStream("./temp").getChannel();
		fc.read(dst);
		//Flips this buffer. The limit is set to the current position and thenthe position is set to zero. If the mark is defined then it isdiscarded. 
		dst.flip();//
		Charset cs = Charset.defaultCharset();// 返回本地操作系统的字符集
		// Convenience method that decodes bytes in this charset into Unicodecharacters.
		CharBuffer cb = cs.decode(dst);
		System.out.println(cb.toString());
		fc.close();

	}

}
