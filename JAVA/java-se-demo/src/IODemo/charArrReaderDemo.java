package IODemo;

import java.io.CharArrayReader;
import java.io.IOException;

public class charArrReaderDemo {
	public static void main(String[] args) throws IOException {
		char [] buf="人生若只如初见，何事西风悲画扇。等闲变却故人心，却道故人心易变。骊山语罢清宵半，泪雨零铃终不怨。何如薄幸锦衣郎，比翼连枝当日愿!".toCharArray();
		CharArrayReader reader = new CharArrayReader(buf);
		int data =-1;
		while((data = reader.read())!= -1) {
			System.err.print((char)data);
		}
		reader.close();
	}

}
