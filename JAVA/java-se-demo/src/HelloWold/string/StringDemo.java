package HelloWold.string;

import java.io.UnsupportedEncodingException;

public class StringDemo {
	public static void main(String[] args) {
		boolean b = "1234@qq.com".matches("[a-zA-Z0-9]+@[\\w]\\.[a-z]");
		System.out.println(b);

		try {
			String str = new String("×Ö·û´®".getBytes("utf-8"), "utf-8");
			System.out.println(str);
			System.out.println(str.charAt(1));
			System.out.println("HELLO WORLD!".equalsIgnoreCase("hello world!")); // true;
			System.out.println("hello world".indexOf('o'));// 4
			System.out.println("hello World".substring(5, 8));// Wo
			String strPath = "D:/opt/eclipse-java-2018-12-R-win32-x86_64/eclipse/features/org.eclipse.egit_5.2.0.201812061821-r/META-INF/";
			StringBuffer strBufPath = new StringBuffer(strPath);
			String strArr[] =strPath.split("/");
			for(String str1:strArr) {
				System.out.println(str1);
			}
			System.out.println("================");
			strPath=strPath.replaceAll("/", "\\\\");
			System.out.println(strPath);
			
			strPath=strPath.replaceAll("\\\\", "-");
			System.out.println(strPath);
			for(String s:strPath.split("-")) {
				System.out.println(s);
			}
			 
			strBufPath.reverse();
			System.out.println(strBufPath);
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
