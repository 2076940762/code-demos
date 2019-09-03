package IODemo;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class byteArrayInputStreamDemo {
	public static void main(String[] args) {
		byte[] buffer = { 1, 112, -4, -3, 6, -78, 32, 6, 2, 5, 32, 42 };
		ByteArrayInputStream in = new ByteArrayInputStream(buffer);

		int data = in.read();
		while (data != -1) {
			System.out.print(data + " , ");
			data = in.read();
		}
		
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
