package usefulTools;

//�����
import java.util.Scanner;

public class ScannerDemo {
	public static void main(String[] args) {
		// Scanner
		Scanner Sc = new Scanner(System.in);
		int i = Sc.nextInt();
		System.out.println(i);

		// �����ַ���
		String str = Sc.next();
		System.out.println(str);
		
		Sc.close();
	}

}