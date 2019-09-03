package newIO;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Random;

public class BufferView {
	public static void main(String[] args) throws FileNotFoundException {
		final PrintStream OUT =System.out;
		System.setOut(new PrintStream("e:\\tem.txt"));
		
		ByteBuffer buffer = ByteBuffer.allocate(1024 * 4);
		IntBuffer intBuf = buffer.asIntBuffer();
		Random ran = new Random(System.currentTimeMillis());
		for (int i = 0; i < 100; i++) {
			intBuf.put(ran.nextInt(10000));
		}

		// Rewinds this buffer. The position is set to zero and the mark isdiscarded
		intBuf.rewind();
		while (intBuf.hasRemaining()) {
			int i = intBuf.get();
//			if (i != 0) 
			{
				System.out.println(i);
			}
		}
		System.setOut(OUT);
	}
}
