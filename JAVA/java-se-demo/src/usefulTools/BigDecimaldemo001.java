package usefulTools;

import java.math.BigDecimal;

public class BigDecimaldemo001 {
	public static void main(String[] args) {
		
		BigDecimal b1 =new BigDecimal("99999999999999.999999");
		BigDecimal b2 = new BigDecimal("0.000001");
		
		BigDecimal b3 = b1.add(b2);
		
		System.out.println(b3);
		System.out.println(0.00001+0.000001);
		
	}

}
