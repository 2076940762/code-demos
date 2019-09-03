package IODemo;

import java.io.File;
import java.io.IOException;

public class mkdir_touch {
	public static void main(String[] args) {
		//创建文件
		function();
		
		//创建文件夹
		function2();
	}

	private static void function2() {
		File file = new File("F:\\a\\b\\c\\d\\e\\f\\g\\");
		if(file.exists()) {
			file.delete();
		}
		
		boolean b = file.mkdirs();
		if(b) {
			System.out.println("successfully Created dir ");
		}
		else {
			System.out.println("falied to create dir");
		}
	}

	private static void function() {
		File file1 =new File("f:\\abcd");		
		if(file1.exists())
		{
			System.out.println("Exist");			
			file1.delete();
		}
		else
		{
			System.out.println("no such file");
			boolean b =false;
				try{
					 b = file1.createNewFile();
				}
				catch(IOException ioe)
				{
					System.out.println(ioe.getStackTrace());
				}
				
				if(!b)
				{
					System.out.println("Failed to create file");
				}
				else {
					System.out.println("successfully createed file");
				}
		}
			
	}

}
