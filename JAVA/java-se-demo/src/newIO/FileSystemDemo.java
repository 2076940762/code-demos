package newIO;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileSystemDemo {
	public static void main(String[] args) throws Exception {
		copyFile("E:\\零基础直达六级水平[新东方六级]\\Step4.实践进步\\新概念二册\\lesson 30\\","d:\\","30_3_课文讲解2.mp4");
		MoveFile("e:\\", "D:\\", "课表.xlsx");
		
		mkDir("e:\\a\\b\\c");
		ll("e:\\");//不递归
	}

	public static void copyFile(String strSrc, String strDest, String strFileName) throws Exception {
		if (strSrc == null || strDest == null || strFileName == null) {
			throw new Exception("参数不合法");
		}

		Path pathSrc = Paths.get(strSrc, strFileName);
		Path pathDest = Paths.get(strDest, strFileName);
		
		Files.copy(pathSrc, pathDest, StandardCopyOption.REPLACE_EXISTING);
	}

	public static void MoveFile(String strSrc, String strDest, String strFileName) throws Exception {
		if (strSrc == null || strDest == null || strFileName == null) {
			throw new Exception("参数不合法");
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
