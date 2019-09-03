package IODemo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BufferedCopyTxtFile {
	public static void main(String[] args) throws IOException {
		BufferedReader reader =new BufferedReader(new InputStreamReader(new FileInputStream("f:\\netstandard.xml"),"utf-8" ));
		BufferedWriter writer  = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("f:\\a.xml"),"GBK"));
		
		String line = null;
		while((line = reader.readLine()) != null) {
			writer.write(line);
			//System.out.println(line);
			
		}
		
		reader.close();
		writer.close();
	}

}
