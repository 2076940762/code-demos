package IODemo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class copyGBK2UTF8 {
	public static void main(String[] args) throws IOException {
		
		FileInputStream fSrc = new FileInputStream("F:\\src.txt");		
		InputStreamReader reader = new InputStreamReader(fSrc);
		
		FileOutputStream fDest =new FileOutputStream("F:\\dest.txt");
		OutputStreamWriter writer = new OutputStreamWriter(fDest,"utf-8");
		
		char buffer []= new char[1024*4];
		int len =0;
		
		while((len = reader.read(buffer)) != -1) {
			writer.write(buffer, 0, len);
		}
		
		reader.close();
		writer.close();
		
	}

}
