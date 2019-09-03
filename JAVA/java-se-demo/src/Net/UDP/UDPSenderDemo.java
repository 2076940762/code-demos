package Net.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPSenderDemo {
	public static void main(String[] args) throws IOException {
		InetAddress inet = InetAddress.getByName("127.0.0.1");
		@SuppressWarnings("resource")
		DatagramSocket socket = new DatagramSocket();				
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);		
		
		while(true) {
			String line = sc.nextLine();
			byte [] buffer = line.getBytes();
			DatagramPacket data = new DatagramPacket(buffer, buffer.length,inet,6000);				
			socket.send(data);
		}
				
		//socket.close();
	}

}
