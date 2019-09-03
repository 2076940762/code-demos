package Net.TCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TcpClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		@SuppressWarnings("resource")
		Socket socket1 = new Socket("127.0.0.1", 8080);		
		OutputStream out = socket1.getOutputStream();
		InputStream in = socket1.getInputStream();
		
		byte SendBuffer [] = new byte[1024];
		
		while(true) {
			@SuppressWarnings("resource")
			Scanner sc =new Scanner(System.in);
			String line = sc.nextLine();			
			out.write(line.getBytes());
			
			int len = in.read(SendBuffer);
			System.out.println(new String(SendBuffer,0,len));
			
		}
	}

}
