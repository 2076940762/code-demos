package IODemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class echo {
	public static void main(String[] args) throws IOException {
		InputStreamReader r = new InputStreamReader(System.in);
		BufferedReader resder = new BufferedReader(r);
		String data = null;
		System.out.println("echo:");

		while ((data = resder.readLine()) != null) {
			System.out.println(data);
		}
		resder.close();
	}

}
