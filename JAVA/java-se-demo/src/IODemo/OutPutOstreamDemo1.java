package IODemo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class OutPutOstreamDemo1 {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException ,RuntimeException{
		File file = new File("F:\\a.txt");
		
		if(!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
					return;
				}
		}
		
		FileOutputStream  fd  = null;
		try {
			fd = new FileOutputStream(file,true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();		
			return ;
		}
		
		if(fd == null) {
			throw new IOException("Failed to open the file");
		}
		
		try {
			fd.write(-1);
			fd.write("hello".getBytes());
		}
		catch(IOException e){
			e.printStackTrace();
			throw new RuntimeException( "Failed to Write the file");
		}
		finally {
			fd.close();
		}
				
	}

}
