package IODemo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BigFileCopy {
	public static void main(String[] args) throws IOException {
		
		long begin = System.currentTimeMillis();
		
		BufferedInputStream inner =new BufferedInputStream(new FileInputStream("E:\\Download\\Mulholland.Drive.2001.1080p.JPN.BluRay.x264-CtrlHD\\Mulholland.Drive.2001.1080p.JPN.BluRay.x264-CtrlHD.mkv"));
		BufferedOutputStream outer = new BufferedOutputStream(new FileOutputStream("f:\\movie.mkv"));
		
		byte [] buffer = new byte[1024*16];
		int len =0;
		
		while((len = inner.read(buffer)) != -1) {
			outer.write(buffer, 0, len);
			System.out.println(len);
		}
		
		inner.close();
		outer.close();
		
		long end = System.currentTimeMillis();
		
		System.out.println("used"+(end - begin) +"millisecond");
	}

}
