package IODemo;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class pipeDemo {
	public static PipedOutputStream out = new PipedOutputStream();
	public static void main(String[] args) {
//		PipedOutputStream out = new PipedOutputStream();
		new Thread(() -> {
			for (int i = 0; i < 1000000; i++) {
				try {
					out.write(i);
				} catch (IOException e) {
					e.printStackTrace();
				} finally {

				}
			}
		}).start();

		new Thread(() -> {
			PipedInputStream in = null;
			try {
				in = new PipedInputStream(out);
				byte [] bufer =new byte[64];
				int len =-1;
				while ((len = in.read(bufer))!= -1) {
					for (int i = 0; i < len; i++) {
						System.out.println(bufer[i]);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					in.close();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
//		new Thread(() -> {
//			PipedInputStream in = null;
//			try {
//				in = new PipedInputStream(out);
//				int len = in.read();
//				while (len!= -1) {
//					System.out.println(len);
//					len = in.read();
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			} finally {
//				try {
//					in.close();
//					out.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}).start();
		
		
//		try {
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			out.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

	}
}
