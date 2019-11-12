package Net.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpReciver {
	public static void main(String[] args) throws IOException {

		byte buffer[] = new byte[1024];
		DatagramPacket pack = new DatagramPacket(buffer, buffer.length);

		@SuppressWarnings("resource")
		DatagramSocket socket = new DatagramSocket(6000);

		while (true) {
			socket.receive(pack);

			// ²ð°ü
			String strIp = pack.getAddress().getHostAddress();
			int Port = pack.getPort();
			int len = pack.getLength();

			System.out.println(new String(buffer, 0, len) + " from " + strIp + +Port);
		}

		// socket.close();
	}
}
