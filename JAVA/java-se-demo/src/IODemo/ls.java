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
		
		//��ʾ��ǰ�ļ����µ��ļ���
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
//		f:\2014���ȫ��linux��ά�̳�
//		f:\2018-2019ѧ���һѧ���ܽ�-������.docx
//		f:\C++
//		f:\git-test
//		f:\java������Ƶ��Դ ����&���� ��ҵ����Ƶ 2017-2018������ȫ����Ƶ�̳�
//		f:\OS��ʫ.pptx
//		f:\PPTģ��.zip
//		f:\Software
//		f:\System Volume Information
//		f:\��������-�ҹ���
//		f:\��ҵ����
//		f:\��ʫ���ʹǵ䣨�޶��桤�Ϻ���������磩.pdf
//		f:\�δʼ��ʹ�ǵ�(������).pdf
//		f:\���ݿ�ϵͳ���ۣ�����ƪ��-�й������ѧ-��ɺ����С�¡��º��
//		f:\���ݿ�ϵͳ���ۣ��߼�ƪ��-�й������ѧ-��ɺ����С�¡��º��
//		f:\��Ӱ
//		f:\������ҵ��ѧͳһ֧��ƽ̨.pdf
//		f:\Ѹ������
		
	}

}















