package Net.TCP;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;

public class WorkThread extends Thread{
	
		public WorkThread(Socket socket) {
		super();
		this.socket = socket;
	}

		
		@Override
		public void run() {
			try {
				
				File file = new File( "e:\\download");
				if(!file.exists()) {
					file.mkdirs();
				}
				
				String strFileName = ""+System.currentTimeMillis()+"-"+new Random(999999).nextInt()+".pdf";
				BufferedOutputStream outFileStream = new BufferedOutputStream( new FileOutputStream(file+File.separator+strFileName));
								
				InputStream in = socket.getInputStream();
				byte [] receive = new byte[1024*64];
				int len =0;
				
				while((len = in.read(receive)) != -1) {
					outFileStream.write(receive,0,len);
					outFileStream.flush();
				}
				outFileStream.close();
				
				OutputStream out = socket.getOutputStream();
				out.write("Success".getBytes());				
					
				out.close();
				in.close();
				outFileStream.close();
				 
			} catch (IOException e) {

				e.printStackTrace();
			}
		}


		private Socket socket ; 
}
