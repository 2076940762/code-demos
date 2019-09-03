package findBigFileInGit;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class findBigFile {
	private static int IgnoreSize = 90; // git 仓库中大于50M则使用git lfs 来上传
	private static String strRepositoryRootDir = ""; // git 仓库根目录

	private static File bigFile = null; // .sh 文件对象
	public static PrintWriter lagreFileWriter = null;

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		// 输入git仓库根目录
		System.out.println("please input git repository root directory!");
		Scanner sc = new Scanner(System.in);
		String strRepRootDir = sc.nextLine();
		if (strRepRootDir == null || strRepRootDir.isEmpty()) {
			throw new RuntimeException("strRepRootDir == null || strRepRootDir.isEmpty()");
		}
		strRepositoryRootDir = strRepRootDir.trim();

		// 判断目录是否存在
		{
			File rootDir = new File(strRepositoryRootDir);
			if (!rootDir.isDirectory() || !rootDir.exists()) {
				throw new RuntimeException("the git root dir is not a dir or not exists!");
			}
		}
		{
			char lastch = strRepositoryRootDir.charAt(strRepositoryRootDir.length() - 1);
			if (lastch != '\\' || lastch != '/') {
				strRepositoryRootDir += "\\";
			}
		}
		{
			String strtemp = strRepositoryRootDir.replace("/", "\\");
			strRepositoryRootDir = strtemp;
		}

		// 结果文件
		bigFile = new File(strRepositoryRootDir + "bigFile.sh");
//		lagreFileWriter = new PrintWriter(bigFile, "utf-8");
		lagreFileWriter = new PrintWriter(bigFile);
		if (lagreFileWriter == null) {
			throw new RuntimeException("lagreFileWriter ==null");
		}
		lagreFileWriter.write("#!/bin/bash\n");
		lagreFileWriter.write("rm \".gitattributes\" -rf \n");

		// 开始查找大文件
		findFile(strRepositoryRootDir);

		lagreFileWriter.close();
		sc.close();

		System.out.println("====end=====");
	}

	private static void findFile(String strRootDir) throws IOException {
		File rootDir = new File(strRootDir);
		File[] subFiles = rootDir.listFiles();
		if (subFiles != null && subFiles.length > 0) {
			for (int i = 0; i < subFiles.length; i++) {
				System.out.println(subFiles[i].getAbsolutePath());

				if (subFiles[i].isFile()) {
					// 处理文件
					if (subFiles[i].length() / 1024 / 1024 >= IgnoreSize) {
						String strFileAbsolutePath = subFiles[i].getAbsolutePath();
						String filePath = strFileAbsolutePath.substring(strRepositoryRootDir.length());
//						git lfs track "*.iso"
						lagreFileWriter.write("git lfs track  \"" + filePath + "\"  \n");
					}

				} else if (subFiles[i].isDirectory() && subFiles[i].toString().lastIndexOf(".git") == -1) {
					// 处理目录
					findFile(subFiles[i].getAbsolutePath());
				}
			}
		}

	}
}
