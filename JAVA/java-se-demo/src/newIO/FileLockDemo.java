package newIO;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.channels.FileLock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FileLockDemo {
	public static void main(String[] args) throws IOException, InterruptedException {
		String strFile = "./a.txt";
		File f = new File(strFile);
		if (f.exists()) {
			f.delete();
			f.createNewFile();
		}
		@SuppressWarnings("resource")
		FileChannel fc = new RandomAccessFile(f, "rws").getChannel();
		MappedByteBuffer mapBuf = fc.map(MapMode.READ_WRITE, 0, 1024 * 8);
		mapBuf.put("hello world!".getBytes());
		
		Lock lock =new ReentrantLock();

		Runnable r1 = () -> {
			try {
				FileLock fFock1 = fc.lock(0, 1024 * 4, false);
				mapBuf.clear();
				for (int i = 12; i < 4 * 1024; i++) {
					lock.lock();
					mapBuf.put(i,(byte)97);
					lock.unlock();
				}
				fFock1.release();
			} catch (IOException e) {
				e.printStackTrace();
			}

		};
		Thread t1 = new Thread(r1);
		t1.start();

		Runnable r2 = () -> {
			try {
				FileLock fFock2 = null;
				do {
					fFock2 = fc.tryLock(1024 * 4 , 1024 * 8, false);
				} while (fFock2 == null);
				mapBuf.clear();
				for (int i = 1024 * 4 ; i < 8 * 1024; i++) {
					lock.lock();
					mapBuf.put(i,(byte)98);;// Encodes this String into a sequence of bytes using theplatform's
					lock.unlock();
				}
				fFock2.release();
			} catch (IOException e) {
				e.printStackTrace();
			}
		};
		Thread t2 = new Thread(r2);
		t2.start();

		t1.join();
		t2.join();
		fc.close();

	}
}
