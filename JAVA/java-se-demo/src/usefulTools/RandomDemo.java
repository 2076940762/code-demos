package usefulTools;

import java.util.Random;

public class RandomDemo {
	public static void main(String[] agrs) {
		Random ran = new Random();

		int max = -1, min = 100;

		for (int i = 0; i < 100000; i++) {
			int temp = ran.nextInt(100);

			if (temp > max) {
				max = temp;
			}

			if (temp < min) {
				min = temp;
			}

			System.out.println(temp);
		}

		System.out.println("max = " + max);
		System.out.println("min = " + min);

		for (int i = 0; i < 10000; i++) {
			double d = ran.nextDouble();
			System.out.println(d);

		}

	}
}