package IODemo;

import java.io.IOException;
import java.io.StringReader;

public class StringReaderDemo {
	public static void main(String[] args) throws IOException {
		String strSrc ="人生若只如初见，何事西风悲画扇。等闲变却故人心，却道故人心易变。骊山语罢清宵半，泪雨零铃终不怨。何如薄幸锦衣郎，比翼连枝当日愿!";
		System.out.println("len:"+strSrc.length());
		StringReader reader = new StringReader(strSrc);
		int data=0;
		while((data=reader.read()) != -1) {
			System.out.print((char)data);
			if (data == '。') {
				System.out.println();
			}
		}
		reader.close();
	}

}
