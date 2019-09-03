package File.Office.POI.Word;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Random;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalJc;

public class docxWrite {
	public static void main(String[] args) throws FileNotFoundException {
		XWPFDocument docx = new XWPFDocument();
		{
			// setAlignment()ָ��Ӧ�����ڴ˶����е��ı��Ķ�����뷽ʽ��CENTER LEFT...
			// p1.setBorderBetween(Borders.APPLES);
			// p1.setBorderBottom(Borders.APPLES);
			// p1.setBorderLeft(Borders.APPLES); ָ��Ӧ��ʾ�����ҳ��ָ������Χ�ı߽硣
			// p1.setBorderRight(Borders.ARCHED_SCALLOPS);ָ��Ӧ��ʾ���Ҳ��ҳ��ָ������Χ�ı߽硣
			// p1.setBorderTop(Borders.ARCHED_SCALLOPS);
			// ָ��Ӧ��ʾ�Ϸ�һ������ͬ��һ��α߽����õĶ���ı߽硣�⼸���ǶԶ���֮��ĸ�ʽ��ͳһ���൱�ڸ�ʽˢ
			// p1.setFirstLineIndent(99); //---���Ŀ�Ȼ���΢��խ
			// p1.setFontAlignment(1); //---����Ķ��뷽ʽ 1�� 2�� 3�� 4���� �� ����д0�͸���
			// p1.setIndentationFirstLine(400); //---��������,ָ�������������Ӧ�����ڸ��εĵ�һ�С�
			// p1.setIndentationHanging(400);
			// //---����ǰ��,ָ������������Ӧͨ����һ�лص���ʼ���ı����ķ������ƶ������Ӹ��εĵ�һ����ɾ����
			// p1.setIndentationLeft(400);
			// //---�������������ƣ�ָ��ӦΪ�����ҶΣ��öε����ݵ���ߵ�Ե����һ��������ߵľ���ұ��ı��߾�����Ȩ�е��Ƕ��ı����ұ�Ե֮�������,���ʡ�Դ����ԣ���Ӧ�ٶ���ֵΪ�㡣
			// p1.setIndentationRight(400);
			// //---ָ��Ӧ������һ�Σ��öε����ݴ����Ҷε��ұ�Ե����ȷ�ı��߾���ұ��ı��߾�����Ȩ�е��Ƕ��ı����ұ�Ե֮�������,���ʡ�Դ����ԣ���Ӧ�ٶ���ֵΪ�㡣
			// p1.setIndentFromLeft(400); //---��������
			// p1.setIndentFromRight(400);
			// p1.setNumID(BigInteger.TEN);
			// p1.setPageBreak(true); //--ָ������Ⱦ�˷�ҳ��ͼ�е��ĵ�����һ�ε����ݶ��������ĵ��е���ҳ�Ŀ�ʼ��
			// p1.setSpacingAfter(6); //--ָ��Ӧ������ĵ��о��Ե�λ��һ�ε����һ��֮��ļ�ࡣ
			// p1.setSpacingAfterLines(6); //--ָ��Ӧ����ڴ��ߵ�λ���ĵ��еĶ�������һ��֮��ļ�ࡣ
			// p1.setSpacingBefore(6); //--ָ��Ӧ���������һ���ĵ��о��Ե�λ�еĵ�һ�еļ�ࡣ
			// p1.setSpacingBeforeLines(6); //--ָ��Ӧ����ڴ��ߵ�λ���ĵ��еĶ���ĵ�һ��֮ǰ�ļ�ࡣ
			// p1.setSpacingLineRule(LineSpacingRule.AT_LEAST); //--ָ����֮��ļ����μ���洢���������С�
			// p1.setStyle("");//--�˷����ṩ����ʽ�Ķ��䣬��ǳ�����.
			// p1.setVerticalAlignment(TextAlignment.CENTER); //---ָ�����ı��Ĵ�ֱ���뷽ʽ��Ӧ���ڴ˶����е��ı�
			// p1.setWordWrapped(true); //--��Ԫ��ָ���Ƿ�������Ӧ�жϳ���һ�е��ı���Χ��ͨ����������� ����������ȼ���
			// �����л�ͨ���ƶ�����һ�� ���ڴʻ�����ϴ��ƣ� ����ʵ��������֡�

			XWPFParagraph para1 = docx.createParagraph();
//			para1.setSpacingAfterLines(500);             //�κ���
			para1.setSpacingAfter(1000);

			// ���ж���
			para1.setAlignment(ParagraphAlignment.CENTER);
			// ��������
			para1.setIndentationFirstLine(400);
			String strPara1 = "�󽭶�ȥ�����Ծ���ǧ�ŷ�������������ߣ��˵��ǣ��������ɳ�ڡ���ʯ���գ������İ�������ǧ��ѩ����ɽ�续��һʱ���ٺ��ܡ�ң�빫誵��꣬С�ǳ����ˣ�����Ӣ���������ڽ�̸Ц�䣬���ֻҷ����𡣹ʹ����Σ�����ӦЦ�ң������������������Σ�һ�������¡�";

			// һ��XWPFRun���������ͬ���Ե�һ������
			XWPFRun run1 = para1.createRun();
			run1.setFontSize(12); // �����С
//			@param rgbStr - the desired color, in the hex form "RRGGBB".
			run1.setColor("FF8833"); // �ı���ɫ
			run1.setFontFamily("Courier New");// ����
			run1.setItalic(true); // б��
			run1.setUnderline(UnderlinePatterns.DOT_DASH);// �»���
			run1.setUnderlineThemeColor("EE8833"); // �»�����ɫ
			run1.addBreak(); // ���з�

			run1.setText(strPara1);
		}

		// ���
		{
			XWPFTable table = docx.createTable(3, 4);
			// ���10��
			for (int i = 0; i < 10; i++) {
				table.createRow();
			}

			// �������
			CTTblPr tablePr = table.getCTTbl().addNewTblPr();
			// �����
			CTTblWidth width = tablePr.addNewTblW();
			width.setW(BigInteger.valueOf(8000));

			table.setRowBandSize(30);
//			table.setWidth(1);
			table.setColBandSize(1);
//			table.setCellMargins(1, 1, 100, 30);

			table.setStyleID("finest");

			int i = 0;
			List<XWPFTableRow> rowList = table.getRows();
			for (XWPFTableRow row : rowList) {
				row.setHeight(14);
				{
					int newCells = new Random().nextInt(10) + 1;
					for (int k = 0; k < newCells; k++) {
						row.addNewTableCell();
					}
				}
				List<XWPFTableCell> cellList = row.getTableCells();
				for (XWPFTableCell cell : cellList) {
					cell.setText(String.valueOf(i++));
					cell.setColor("FF5522");
					// ��Ԫ������
					CTTcPr cellPr = cell.getCTTc().addNewTcPr();
					cellPr.addNewVAlign().setVal(STVerticalJc.CENTER);
//					cell.setWidth("250");
				}
			}

		}

		// ͼƬ
		{
			String imageFiles[] = { "image1.emf.emf", "image10.emf.emf", "image11.emf.emf", "image12.emf.emf",
					"image13.emf.emf", "image14.emf.emf", "image15.emf.emf", "image16.emf.emf", "image17.emf.emf",
					"image18.emf.emf", "image19.emf.emf", "image2.emf.emf", "image20.emf.emf", "image21.emf.emf",
					"image22.emf.emf", "image23.emf.emf", "image24.emf.emf", "image25.emf.emf", "image26.emf.emf",
					"image27.emf.emf", "image3.emf.emf", "image4.emf.emf", "image5.emf.emf", "image6.emf.emf",
					"image7.emf.emf", "image8.emf.emf", "image9.emf.emf" };

			for (String str : imageFiles) {
				str = str.trim();
			}

			XWPFRun run2 = docx.createParagraph().createRun();
			for (String imgFile : imageFiles) {
				int format;
				if (imgFile.endsWith(".emf"))
					format = XWPFDocument.PICTURE_TYPE_EMF;
				else if (imgFile.endsWith(".wmf"))
					format = XWPFDocument.PICTURE_TYPE_WMF;
				else if (imgFile.endsWith(".pict"))
					format = XWPFDocument.PICTURE_TYPE_PICT;
				else if (imgFile.endsWith(".jpeg") || imgFile.endsWith(".jpg"))
					format = XWPFDocument.PICTURE_TYPE_JPEG;
				else if (imgFile.endsWith(".png"))
					format = XWPFDocument.PICTURE_TYPE_PNG;
				else if (imgFile.endsWith(".dib"))
					format = XWPFDocument.PICTURE_TYPE_DIB;
				else if (imgFile.endsWith(".gif"))
					format = XWPFDocument.PICTURE_TYPE_GIF;
				else if (imgFile.endsWith(".tiff"))
					format = XWPFDocument.PICTURE_TYPE_TIFF;
				else if (imgFile.endsWith(".eps"))
					format = XWPFDocument.PICTURE_TYPE_EPS;
				else if (imgFile.endsWith(".bmp"))
					format = XWPFDocument.PICTURE_TYPE_BMP;
				else if (imgFile.endsWith(".wpg"))
					format = XWPFDocument.PICTURE_TYPE_WPG;
				else {
					System.err.println("Unsupported picture: " + imgFile
							+ "Expected emf|wmf|pict|jpeg|png|dib|gif|tiff|eps|bmp|wpg");
					continue;
				}
				try {
					run2.addPicture(new FileInputStream(imgFile), format, imgFile, Units.toEMU(200), Units.toEMU(200));
				} catch (InvalidFormatException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

		FileOutputStream out = new FileOutputStream("./PIO-tetsing.docx");
		try {
			docx.write(out);
			out.close();
			docx.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
