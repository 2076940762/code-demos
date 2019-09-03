package date_time;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatDemo {
	public static void main(String[] args) {
		DateFormat dft =new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		String dtrDate = dft.format( new Date().getTime());
		
		System.out.println(dtrDate);
	}

}
