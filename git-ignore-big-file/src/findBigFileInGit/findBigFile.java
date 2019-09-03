package findBigFileInGit;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class findBigFile {
	private static int IgnoreSize = 90; // git �ֿ��д���50M��ʹ��git lfs ���ϴ�
	private static String strRepositoryRootDir = ""; // git �ֿ��Ŀ¼

	private static File bigFile = null; // .sh �ļ�����
	public static PrintWriter lagreFileWriter = null;

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		// ����git�ֿ��Ŀ¼
		System.out.println("please input git repository root directory!");
		Scanner sc = new Scanner(System.in);
		String strRepRootDir = sc.nextLine();
		if (strRepRootDir == null || strRepRootDir.isEmpty()) {
			throw new RuntimeException("strRepRootDir == null || strRepRootDir.isEmpty()");
		}
		strRepositoryRootDir = strRepRootDir.trim();

		// �ж�Ŀ¼�Ƿ����
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

		// ����ļ�
		bigFile = new File(strRepositoryRootDir + "bigFile.sh");
//		lagreFileWriter = new PrintWriter(bigFile, "utf-8");
		lagreFileWriter = new PrintWriter(bigFile);
		if (lagreFileWriter == null) {
			throw new RuntimeException("lagreFileWriter ==null");
		}
		lagreFileWriter.write("#!/bin/bash\n");
		lagreFileWriter.write("rm \".gitattributes\" -rf \n");

		// ��ʼ���Ҵ��ļ�
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
					// �����ļ�
					if (subFiles[i].length() / 1024 / 1024 >= IgnoreSize) {
						String strFileAbsolutePath = subFiles[i].getAbsolutePath();
						String filePath = strFileAbsolutePath.substring(strRepositoryRootDir.length());
//						git lfs track "*.iso"
						lagreFileWriter.write("git lfs track  \"" + filePath + "\"  \n");
					}

				} else if (subFiles[i].isDirectory() && subFiles[i].toString().lastIndexOf(".git") == -1) {
					// ����Ŀ¼
					findFile(subFiles[i].getAbsolutePath());
				}
			}
		}

	}
}
