package LambdaDemo;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public class lambdatet001 {
	public static void main(String[] args) {
		String strData[] = { "java", "javaScripe","SQL","java SE","java EE", "cpp", "c#", "c", "php","R","python","shell" };
		List<String> listData = Arrays.asList(strData);
		// ������ʽһ
		for (String str : listData) {
			System.out.println(str);
		}

		System.out.println("\n===================\n");

		// ������ʽ��lambda1
		listData.forEach((str) -> System.out.println(str));

		// lambda2
		System.out.println("\n===================\n");
		listData.forEach(System.out::println);


		System.out.println("\n===================\n");

		// lambda�����������ڲ���
		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				System.out.println("threadid:"+Thread.currentThread().getId()+":hello");
			}
		}).start();
		
		//���� �����ڲ���ʵ��
		Comparator<String> com1= new Comparator<String>() {
			public int compare(String str1,String str2) {
				return str1.compareTo(str2);
			}
		};
		
		Collections.sort(listData, com1);
		System.out.println("after sorted 1");
		listData.forEach(System.out::println);
		
		//���� lambda���ʽʵ��
		Comparator<String> com2= (String str1,String str2)->(str2.compareTo(str1));
		Collections.sort(listData, com2);
		System.out.println("after sorted 2");
		listData.forEach(System.out::println);

	}
}











