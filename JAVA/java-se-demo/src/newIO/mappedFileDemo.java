package newIO;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.zip.CRC32;

public class mappedFileDemo {
	public static void main(String[] args) throws IOException {
		try {
			long crcValue =getCrc32("E:\\Document\\JAVA\\Java JDK 8学习笔记  清华大学出版社.pdf");
			String strCrcVal = Long.toString(crcValue);
			System.out.println(strCrcVal);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static long getCrc32(String strFile) throws Exception {
		File f = new File(strFile);
		if (!f.exists()) {
			throw new Exception(strFile + " is not exist");
		}

		if (!f.isFile()) {
			throw new Exception(strFile + "is not a normal file");
		}

		RandomAccessFile raf = new RandomAccessFile(f, "rw");
		MappedByteBuffer buffer = raf.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, f.length());
		CRC32 crc =new CRC32();
		while(buffer.hasRemaining()) {
			crc.update(buffer.get());
		}
		raf.close();
		return crc.getValue();
	}
}
