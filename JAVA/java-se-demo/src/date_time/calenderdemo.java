package date_time;

import java.util.Calendar;

public class calenderdemo {
	public static void main(String[] args) {
		Calendar cal =Calendar.getInstance();
		System.out.println(cal);
		
		System.out.println(cal.get(Calendar.YEAR));
		
		cal.set(Calendar.YEAR, 2089);
		
		System.out.println(cal.getTime());
	}

}
