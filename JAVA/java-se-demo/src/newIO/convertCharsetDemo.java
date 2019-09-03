package newIO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class convertCharsetDemo {
	public static void main(String[] args) throws IOException {
		String str = "寒蝉凄切，对长亭晚，骤雨初歇。都门帐饮无绪，留恋处，兰舟催发。执手相看泪眼，竟无语凝噎。念去去，千里烟波，暮霭沉沉楚天阔。多情自古伤离别，更那堪冷落清秋节。今宵酒醒何处？杨柳岸，晓风残月。此去经年，应是良辰好景虚设。便纵有千种风情，更与何人说！\n";

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
