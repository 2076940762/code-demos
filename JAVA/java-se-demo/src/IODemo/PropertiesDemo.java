package IODemo;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class PropertiesDemo {
	public static void main(String[] args) throws IOException {

		{
			Properties pro = new Properties();

			pro.setProperty("a","1");
			pro.setProperty("b","2");
			pro.setProperty("c","3");
			pro.setProperty("d","14");
			pro.setProperty("e","1");
			pro.setProperty("f","16");
			
			pro.store(new FileWriter("e:\\pro.properties"), "");		
		}
		
		{
			FileReader fd = new FileReader("e:\\pro.properties");
			
			Properties pre = new Properties();
			pre.load(fd);
			fd.close();
			
			System.out.println(pre);
		}
		
		
	}

}
