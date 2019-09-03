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
			// setAlignment()指定应适用于此段落中的文本的段落对齐方式。CENTER LEFT...
			// p1.setBorderBetween(Borders.APPLES);
			// p1.setBorderBottom(Borders.APPLES);
			// p1.setBorderLeft(Borders.APPLES); 指定应显示在左边页面指定段周围的边界。
			// p1.setBorderRight(Borders.ARCHED_SCALLOPS);指定应显示在右侧的页面指定段周围的边界。
			// p1.setBorderTop(Borders.ARCHED_SCALLOPS);
			// 指定应显示上方一组有相同的一组段边界设置的段落的边界。这几个是对段落之间的格式的统一，相当于格式刷
			// p1.setFirstLineIndent(99); //---正文宽度会稍微变窄
			// p1.setFontAlignment(1); //---段落的对齐方式 1左 2中 3右 4往上 左 不可写0和负数
			// p1.setIndentationFirstLine(400); //---首行缩进,指定额外的缩进，应适用于父段的第一行。
			// p1.setIndentationHanging(400);
			// //---首行前进,指定的缩进量，应通过第一行回到开始的文本流的方向上移动缩进从父段的第一行中删除。
			// p1.setIndentationLeft(400);
			// //---整段缩进（右移）指定应为从左到右段，该段的内容的左边的缘和这一段文字左边的距和右边文本边距和左段权中的那段文本的右边缘之间的缩进,如果省略此属性，则应假定其值为零。
			// p1.setIndentationRight(400);
			// //---指定应放置这一段，该段的内容从左到右段的右边缘的正确文本边距和右边文本边距和左段权中的那段文本的右边缘之间的缩进,如果省略此属性，则应假定其值为零。
			// p1.setIndentFromLeft(400); //---整段右移
			// p1.setIndentFromRight(400);
			// p1.setNumID(BigInteger.TEN);
			// p1.setPageBreak(true); //--指定当渲染此分页视图中的文档，这一段的内容都呈现在文档中的新页的开始。
			// p1.setSpacingAfter(6); //--指定应添加在文档中绝对单位这一段的最后一行之后的间距。
			// p1.setSpacingAfterLines(6); //--指定应添加在此线单位在文档中的段落的最后一行之后的间距。
			// p1.setSpacingBefore(6); //--指定应添加上面这一段文档中绝对单位中的第一行的间距。
			// p1.setSpacingBeforeLines(6); //--指定应添加在此线单位在文档中的段落的第一行之前的间距。
			// p1.setSpacingLineRule(LineSpacingRule.AT_LEAST); //--指定行之间的间距如何计算存储在行属性中。
			// p1.setStyle("");//--此方法提供了样式的段落，这非常有用.
			// p1.setVerticalAlignment(TextAlignment.CENTER); //---指定的文本的垂直对齐方式将应用于此段落中的文本
			// p1.setWordWrapped(true); //--此元素指定是否消费者应中断超过一行的文本范围，通过打破这个词 （打破人物等级）
			// 的两行或通过移动到下一行 （在词汇层面上打破） 这个词的拉丁文字。

			XWPFParagraph para1 = docx.createParagraph();
//			para1.setSpacingAfterLines(500);             //段后间距
			para1.setSpacingAfter(1000);

			// 居中对齐
			para1.setAlignment(ParagraphAlignment.CENTER);
			// 首行缩进
			para1.setIndentationFirstLine(400);
			String strPara1 = "大江东去，浪淘尽，千古风流人物。故垒西边，人道是，三国周郎赤壁。乱石穿空，惊涛拍岸，卷起千堆雪。江山如画，一时多少豪杰。遥想公瑾当年，小乔初嫁了，雄姿英发。羽扇纶巾，谈笑间，樯橹灰飞烟灭。故国神游，多情应笑我，早生华发。人生如梦，一尊还酹江月。";

			// 一个XWPFRun代表具有相同属性的一个区域
			XWPFRun run1 = para1.createRun();
			run1.setFontSize(12); // 字体大小
//			@param rgbStr - the desired color, in the hex form "RRGGBB".
			run1.setColor("FF8833"); // 文本颜色
			run1.setFontFamily("Courier New");// 字体
			run1.setItalic(true); // 斜体
			run1.setUnderline(UnderlinePatterns.DOT_DASH);// 下划线
			run1.setUnderlineThemeColor("EE8833"); // 下划线颜色
			run1.addBreak(); // 换行符

			run1.setText(strPara1);
		}

		// 表格
		{
			XWPFTable table = docx.createTable(3, 4);
			// 添加10行
			for (int i = 0; i < 10; i++) {
				table.createRow();
			}

			// 表格属性
			CTTblPr tablePr = table.getCTTbl().addNewTblPr();
			// 表格宽度
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
					// 单元格属性
					CTTcPr cellPr = cell.getCTTc().addNewTcPr();
					cellPr.addNewVAlign().setVal(STVerticalJc.CENTER);
//					cell.setWidth("250");
				}
			}

		}

		// 图片
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
