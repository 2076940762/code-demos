package newIO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

public class CPInNewIO {
	public static void main(String[] args) throws IOException {
		String strSrc=null,StrDest=null;
		Scanner sc =new Scanner(System.in);
		System.out.println("please input Src file path:");
		strSrc=sc.nextLine();
		System.out.println("please input Dest file path:");
		StrDest = sc.nextLine();
		sc.close();
		
		@SuppressWarnings("resource")
		FileChannel fcIn=new FileInputStream(strSrc).getChannel();
		@SuppressWarnings("resource")
		FileChannel fcOut =new FileOutputStream(StrDest).getChannel();
		ByteBuffer buffer =ByteBuffer.allocate(2048);
		while (fcIn.read(buffer) != -1) {
			// The limit is set to the current position and thenthe position is set to zero. 
			buffer.flip();
			fcOut.write(buffer);
			// The position is set to zero, the limit is set tothe capacity, and the mark is discarded. 
			buffer.clear();
		}
		fcOut.close();
		fcIn.close();
		
	}

}
