package CommonIODemo;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class CopyFielByCommonIO {
	public static void main(String[] args) throws IOException {
		long begin = System.currentTimeMillis();
		
		FileUtils.copyFile(new File("E:\\Download\\Mulholland.Drive.2001.1080p.JPN.BluRay.x264-CtrlHD\\Mulholland.Drive.2001.1080p.JPN.BluRay.x264-CtrlHD.mkv"), 
				new File("C:\\Users\\qingtian\\Desktop\\a.mkv"));
		
		long end = System.currentTimeMillis();
		
		System.out.println(end - begin );//201309
	}

}
