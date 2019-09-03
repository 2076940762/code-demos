package Net.TCP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MutilThredSever {
	
	public static void main(String[] args) throws IOException {

		@SuppressWarnings("resource")
		ServerSocket server = new ServerSocket(8080);
		
		while(true) {
			Socket socket = server.accept();    
		    new WorkThread(socket).start();
		}
	    
	
	}
}
