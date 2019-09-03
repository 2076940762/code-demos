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

//Apache poi��hwpfģ����ר��������word doc�ļ����ж�д�����ġ�
//��hwpf��������ʹ��HWPFDocument����ʾһ��word doc�ĵ���
//��HWPFDocument��������ô�������
//Range������ʾһ����Χ�������Χ�����������ĵ���Ҳ�����������ĳһС�ڣ�Section����Ҳ������ĳһ�����䣨Paragraph������������ӵ�й�ͬ���Ե�һ���ı���CharacterRun����
//Section��word�ĵ���һ��С�ڣ�һ��word�ĵ������ɶ��С�ڹ��ɡ�
//Paragraph��word�ĵ���һ�����䣬һ��С�ڿ����ɶ�����乹�ɡ�
//CharacterRun��������ͬ���Ե�һ���ı���һ����������ɶ��CharacterRun��ɡ�
//Table��һ�����
//TableRow������Ӧ���С�
//TableCell������Ӧ�ĵ�Ԫ��
//TableCell������Ӧ�ĵ�Ԫ��

public class docxRead {
	public static void main(String[] args) throws IOException {
		FileInputStream fileIn = new FileInputStream("Doc/docx/day21-(�쳣)�̰�.docx");
		XWPFDocument docx = new XWPFDocument(fileIn);

		// ����
		List<XWPFParagraph> listParag = docx.getParagraphs();
		for (XWPFParagraph p : listParag) {
			System.out.println(p.getText());
		}

		// ���
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

		// ͼƬ
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
