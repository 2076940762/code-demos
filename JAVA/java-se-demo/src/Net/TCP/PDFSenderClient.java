package Net.TCP;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class PDFSenderClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		BufferedInputStream fileIn = new BufferedInputStream(new FileInputStream("E:\\Document\\JAVA\\Java编程思想第四版完整中文高清版.pdf"));
		System.out.println("===========");
		Socket socket = new Socket("127.0.0.1",8080);
		OutputStream out = socket.getOutputStream();		

		byte buffer[] = new byte[1024];
		int len = 0;
		while((len = fileIn.read(buffer)) != -1) {
			out.write(buffer);
		}
		
		socket.shutdownOutput();
		
		InputStream socketIn = socket.getInputStream();
		
		while((len = socketIn.read(buffer)) != -1) {
			System.out.println(new String(buffer,0,len));
		}
		
		socket.close();
		fileIn.close();
	}

}




