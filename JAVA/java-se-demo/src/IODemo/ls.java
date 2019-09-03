package IODemo;

import java.io.File;

public class ls {
	public static void main(String[] args) {
		function();
	}

	private static void function() {
		File file = new File("f:\\");
		
		if(file.exists()) {
			System.out.println("exits");
		}
		else {
			System.out.println("no such file");
		}
		
		//显示当前文件夹下的文件名
		String strFileList []= file.list();		
		for(String str : strFileList) {
			System.out.println(str);
		}
		
		System.out.println("/************************************/");
	
		File [] fileArr = file.listFiles();
		
		for (File f: fileArr) {
			System.out.println(f);
		}
//		f:\$RECYCLE.BIN
//		f:\2014马哥全套linux运维教程
//		f:\2018-2019学年第一学期总结-李星利.docx
//		f:\C++
//		f:\git-test
//		f:\java最新视频资源 黑马&传智 就业班视频 2017-2018年最新全套视频教程
//		f:\OS古诗.pptx
//		f:\PPT模板.zip
//		f:\Software
//		f:\System Volume Information
//		f:\个人资料-找工作
//		f:\作业汇总
//		f:\唐诗鉴赏辞典（修订版·上海辞书出版社）.pdf
//		f:\宋词鉴赏大辞典(完美版).pdf
//		f:\数据库系统概论（基础篇）-中国人民大学-王珊、杜小勇、陈红等
//		f:\数据库系统概论（高级篇）-中国人民大学-王珊、杜小勇、陈红等
//		f:\电影
//		f:\西北工业大学统一支付平台.pdf
//		f:\迅雷下载
		
	}

}















