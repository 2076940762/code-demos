package date_time;

import java.util.Date;

public class DateDemo {
	public static void main(String[] args) {
		Date date =new Date();
		System.out.println(date);
		
		System.out.println(date.getTime());
		
		date.setTime(1);
		System.out.println(date);
	}

}
