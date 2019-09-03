package File.Office.POI.Word;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

//Apache poi的hwpf模块是专门用来对word doc文件进行读写操作的。
//在hwpf里面我们使用HWPFDocument来表示一个word doc文档。
//在HWPFDocument里面有这么几个概念：
//Range：它表示一个范围，这个范围可以是整个文档，也可以是里面的某一小节（Section），也可以是某一个段落（Paragraph），还可以是拥有共同属性的一段文本（CharacterRun）。
//Section：word文档的一个小节，一个word文档可以由多个小节构成。
//Paragraph：word文档的一个段落，一个小节可以由多个段落构成。
//CharacterRun：具有相同属性的一段文本，一个段落可以由多个CharacterRun组成。
//Table：一个表格。
//TableRow：表格对应的行。
//TableCell：表格对应的单元格。
//TableCell：表格对应的单元格。

public class docxRead {
	public static void main(String[] args) throws IOException {
		FileInputStream fileIn = new FileInputStream("Doc/docx/day21-(异常)教案.docx");
		XWPFDocument docx = new XWPFDocument(fileIn);

		// 段落
		List<XWPFParagraph> listParag = docx.getParagraphs();
		for (XWPFParagraph p : listParag) {
			System.out.println(p.getText());
		}

		// 表格
		List<XWPFTable> TableList = docx.getTables();
		for (XWPFTable table : TableList) {
			List<XWPFTableRow> rows = table.getRows();
			for (XWPFTableRow row : rows) {
				List<XWPFTableCell> cells = row.getTableCells();
				for (XWPFTableCell cell : cells) {
					System.err.print(cell.getText());
				}
				System.out.println();
			}
			System.out.println();
		}

		// 图片
		{
			List<XWPFPictureData> pictureList = docx.getAllPictures();
			for (XWPFPictureData pictureData : pictureList) {
				String strFileName = pictureData.getFileName();
				int type = pictureData.getPictureType();
				String strsuffix = ".";
				switch (type) {
				case XWPFDocument.PICTURE_TYPE_EMF:
					strsuffix = ".emf";
					break;
				case XWPFDocument.PICTURE_TYPE_WMF:
					strsuffix = ".wmf";
					break;
				case XWPFDocument.PICTURE_TYPE_PICT:
					strsuffix = ".pic";
					break;
				case XWPFDocument.PICTURE_TYPE_PNG:
					strsuffix = ".png";
					break;
				case XWPFDocument.PICTURE_TYPE_DIB:
					strsuffix = ".dib";
					break;
				default:
					strsuffix = ".jpg";
				}

				strFileName += strsuffix;
				FileOutputStream fOStream = new FileOutputStream(strFileName);
				fOStream.write((pictureData.getData()));
				fOStream.close();
			}
		}

		docx.close();
	}

}
