package File.PDF.pdfbox;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class PDFWriter {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException {
		PDDocument doc = new PDDocument();
		PDPage page = new PDPage();
		doc.addPage(page);

		PDPageContentStream contentStream = new PDPageContentStream(doc, page);
//		Begin some text operations.
		contentStream.beginText();
		contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
		contentStream.moveTextPositionByAmount(100, 700);

		contentStream.showText("hello world");

		contentStream.endText();
		contentStream.close();

		doc.save("./mpi-pdfbox.pdf");
		doc.close();
	}

}
