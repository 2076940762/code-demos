package File.PDF.pdfbox;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFReader {
	public static void main(String[] args) {
		File file = new File("Doc/PDF/MPI 编程.pdf");
		if (!file.exists()) {
			throw new RuntimeException("文件不存在");
		}

		try {
			PDDocument document = PDDocument.load(file);

			AccessPermission ap = document.getCurrentAccessPermission();
			if (!ap.canExtractContent()) {
				throw new IOException("你没有权限抽取文本");
			}
			
			int pageNum =document.getNumberOfPages();
			
			//读取文本
			PDFTextStripper stripper =new PDFTextStripper();
			stripper.setSortByPosition(true);
			stripper.setStartPage(0);
			stripper.setEndPage(pageNum);
			
			String strContent =stripper.getText(document);
			System.out.println(strContent);
			
			FileOutputStream fOut= new FileOutputStream("mpi.txt");
			fOut.write(strContent.getBytes("utf-8"));
			fOut.flush();
			fOut.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
