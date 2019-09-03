package Net.TCP;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class TcpServer {
	public static void main(String[] args) throws IOException {
		byte Readbuffer[] =new byte[1024];		
		@SuppressWarnings("resource")
		ServerSocket Ssocket =new ServerSocket(8080);
		Socket Sc = Ssocket.accept();		
		InputStream inSStream= Sc.getInputStream() ;	
		java.io.OutputStream OutSStream = Sc.getOutputStream();
		
		while(true) {
			int len = inSStream.read(Readbuffer);		
			String Message = new String(Readbuffer,0,len);
			System.out.println(Message);			
			
			OutSStream.write(("我知道了 " +Message+"  from "+Sc.getInetAddress().getAddress().toString()+":"+Sc.getPort()).getBytes() );
		}
		
		//Ssocket.close();

	}

}
