package usefulTools;

import java.math.BigInteger;

public class BigIntegetDemo {
	public static void main(String[] args) {
		BigInteger b1 =new BigInteger("11111111111111111111111111111111111111111111111");
		BigInteger b2 = new BigInteger("222222222222222222222222222222222222222222");
		
		BigInteger b3 = b2.multiply(b1);
		
		System.out.println(b3);
	}

}
