package IODemo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.SequenceInputStream;

public class seqInoutStreamDemo {
	public static void main(String[] args) {
		FileInputStream f1 = null;
		FileInputStream f2 = null;
		try {
			f1 = new FileInputStream("E:\\Download\\a.txt");
			f2 = new FileInputStream("E:\\Download\\b.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//将两个流合并为一个流
		SequenceInputStream in =new SequenceInputStream(f1,f2);
		byte [] buffer=new byte[64];
		@SuppressWarnings("unused")
		int len = -1;
		try {
			while((len = in.read(buffer)) != -1) {
				for(byte b:buffer) {
					System.out.println(b);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
