package newIO;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileSystemDemo {
	public static void main(String[] args) throws Exception {
		copyFile("E:\\�����ֱ������ˮƽ[�¶�������]\\Step4.ʵ������\\�¸������\\lesson 30\\","d:\\","30_3_���Ľ���2.mp4");
		MoveFile("e:\\", "D:\\", "�α�.xlsx");
		
		mkDir("e:\\a\\b\\c");
		ll("e:\\");//���ݹ�
	}

	public static void copyFile(String strSrc, String strDest, String strFileName) throws Exception {
		if (strSrc == null || strDest == null || strFileName == null) {
			throw new Exception("�������Ϸ�");
		}

		Path pathSrc = Paths.get(strSrc, strFileName);
		Path pathDest = Paths.get(strDest, strFileName);
		
		Files.copy(pathSrc, pathDest, StandardCopyOption.REPLACE_EXISTING);
	}

	public static void MoveFile(String strSrc, String strDest, String strFileName) throws Exception {
		if (strSrc == null || strDest == null || strFileName == null) {
			throw new Exception("�������Ϸ�");
		}

		Path pathSrc = Paths.get(strSrc, strFileName);
		Path pathDest = Paths.get(strDest, strFileName);
		
		Files.move(pathSrc, pathDest, StandardCopyOption.REPLACE_EXISTING);
	}
	
	public static void mkDir(String strdir) {
		Path Dir = Paths.get(strdir);
		if(!Files.exists(Dir)) {
			try {
				Files.createDirectories(Dir);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void ll(String strWorkingDir) throws IOException {
		Path path = Paths.get(strWorkingDir);
		DirectoryStream<Path> paths = Files.newDirectoryStream(path);
		for(Path p:paths) {
			System.out.println(p);
		}
	}
	
}
