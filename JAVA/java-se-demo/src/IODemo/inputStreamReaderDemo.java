package IODemo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class inputStreamReaderDemo {
	public static void main(String[] args) throws IOException {
		FileInputStream f = new FileInputStream("C:\\Users\\qingtian\\Desktop\\java.java");
		InputStreamReader reader = new InputStreamReader(f,"utf-8");
		int data = 0;
		while ((data = reader.read()) != -1) {
			System.out.print((char)data);
		}
		reader.close();
	}
}
