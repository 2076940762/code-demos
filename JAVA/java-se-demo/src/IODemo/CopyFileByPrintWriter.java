package IODemo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class CopyFileByPrintWriter {

	public static void main(String[] args) throws IOException {
		BufferedReader reader =new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\qingtian\\Desktop\\CBS.log"),"utf-8"));
//		PrintWriter writer = new PrintWriter(new FileOutputStream("C:\\Users\\qingtian\\Desktop\\Log.log",true),true);
		PrintWriter writer = new PrintWriter(new File("C:\\Users\\qingtian\\Desktop\\Log.log"),"utf8");
		
		String line =null;
		while((line = reader.readLine()) != null) {
			writer.println(line);
			writer.flush();
		}
		writer.printf("%d", 100);
		writer.printf("%s", "¿Ó–«¿˚");
		
		reader.close();
		writer.close();
	}
}
