package IODemo;
import java.io.File;

public class find {
	public static void main(String[] args) {
		function("d:\\");
		System.out.println("there are "+ total +"  fiels");
	}

	private static void function(String str) {
		File file = new File(str);
		
		//�������ǰĿ¼�µ�dll�ļ�
		File fileArr1 [] = file.listFiles(new findFilter());
		if(fileArr1 != null  && fileArr1.length > 0) {
			for(File f1: fileArr1) {
				System.out.println(f1);
				total++;
			}
		}

		File fileArr [] = file.listFiles();
		//����Ŀ¼
		if(fileArr != null  && fileArr.length > 0) {
			for(File f: fileArr) {			
				if(f.isDirectory())
				{
					function(f.toString());
				}
			}
		}
	}
	private static long total = 0 ;
}
