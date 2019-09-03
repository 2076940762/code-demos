package Net;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressDemo {
	public static void main(String[] args) throws UnknownHostException {
		InetAddress inet = InetAddress.getLocalHost();
		System.out.println(inet.getHostAddress());
		
		InetAddress Inet1 = InetAddress.getByName("www.baidu.com");
		System.out.println(Inet1.getHostAddress());
		
		InetAddress Inet2 = InetAddress.getByName("www.google.com");
		System.out.println(Inet2.getHostAddress());
	}

}
