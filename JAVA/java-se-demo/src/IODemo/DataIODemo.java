package IODemo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DataIODemo {
	public static void main(String[] args) throws IOException {
		File f = new File("./data.txt");
		OutputStream out = null;
		try {
			out = new FileOutputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		DataOutputStream dataOut = new DataOutputStream(out);
		dataOut.writeByte(-12);
		dataOut.writeLong(3000);
		dataOut.writeDouble(3.1415);
		dataOut.writeUTF("hello");
		dataOut.close();
		
		/***********************************************************/
		FileInputStream in = new FileInputStream(f);
		DataInputStream dataIn = new DataInputStream(in);
		System.out.println(dataIn.readByte());
		System.out.println(dataIn.readLong());
		System.out.println(dataIn.readDouble());
		System.out.println(dataIn.readUTF());
		dataIn.close();
	}
}
