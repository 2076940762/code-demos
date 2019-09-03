package IODemo;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class CopyTxtFile {
	public static void main(String[] args) throws IOException{
		Reader fReader =null;
		Writer fWriter = null;
		try {
			fReader = new FileReader("C:\\Users\\qingtian\\Desktop\\aes_256_gcm.txt");
			fWriter = new FileWriter("C:\\Users\\qingtian\\Desktop\\copy.txt");
			
			//缓冲数组
			char [] buffer = new char[1024*4];
			int len = 0;
			
			//复制文件
			while((len = fReader.read(buffer,0,1024*4)) != -1) {
				fWriter.write(buffer,0,len);
				
				//刷新
				fWriter.flush();
			}			
		}
		catch(IOException e)	{
			throw e;
		}
		finally {
			
			try {
				if(fWriter != null) {
					fWriter.close();
				}
			}
			catch(IOException e) {
				throw e;
			}
			finally {
				if(fReader != null) {
					fReader.close();
				}
			}
			
		}
		
	}

}
