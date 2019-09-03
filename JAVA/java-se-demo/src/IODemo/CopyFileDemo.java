package IODemo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class CopyFileDemo {
	public static void main(String[] args) throws RuntimeException, IOException {
		long begin = System.currentTimeMillis();
		String strSrc = null, strDest = null;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("please input source file:");
		strSrc = sc.nextLine();
		
		System.out.println("please input destination file:");
		strDest = sc.nextLine();
		sc.close();

		FileInputStream fileIn = null;
		FileOutputStream fileOut = null;

		// 打开源文件
		fileIn = new FileInputStream(strSrc);

		// 打开目标文件
		try {
			fileOut = new FileOutputStream(strDest);
		} catch (IOException e) {
			fileIn.close();
			throw e;
		}

		// 准备缓冲区
		byte[] buffer = new byte[1024 * 1024];

		// 复制文件
		int len = 0;
		try {
			while ((len = fileIn.read(buffer)) != -1) {
				fileOut.write(buffer, 0, len);
//				System.out.println(len);
				System.out.print('*');
			}
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				fileOut.close();
			} catch (IOException e) {
				throw e;
			} finally {
				fileIn.close();
			}
		}
		long end = System.currentTimeMillis();
		System.out.println(end - begin);
	}// main

}
