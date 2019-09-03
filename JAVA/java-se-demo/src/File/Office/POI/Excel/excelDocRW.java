package File.Office.POI.Excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelDocRW {
	public static void main(String[] args) throws FileNotFoundException, IOException {
//		WriteXlsxDoc("POI-excel.xlsx");
//		ReadXlsxDoc("POI-excel.xlsx");

		WriteXlsDoc("POI-excel.xls");
//		ReadXlsDoc("POI-excel.xls");
	}

	public static void WriteXlsxDoc(final String strFileName) {
		XSSFWorkbook xlsxDoc = new XSSFWorkbook();
		XSSFSheet sheet1 = xlsxDoc.createSheet("helo world");

		for (int i = 0; i < 100; i++) {
			XSSFRow row = sheet1.createRow(i);
			for (int j = 0; j < 1000; j++) {
				XSSFCell cell = row.createCell(j);
				cell.setCellValue(String.valueOf(i * j));
			}
		}

		File file = new File(strFileName);
		if (file.exists()) {
			file.delete();
		}

		try {
			xlsxDoc.write(new FileOutputStream(file));
			xlsxDoc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void ReadXlsxDoc(final String strFileName) throws FileNotFoundException, IOException {
		XSSFWorkbook workBook = new XSSFWorkbook(new FileInputStream(strFileName));
		final int sheetsNum = workBook.getNumberOfSheets();
		for (int i = 0; i < sheetsNum; i++) {
			XSSFSheet sheet = workBook.getSheetAt(i);
			Iterator<org.apache.poi.ss.usermodel.Row> rowIt = sheet.rowIterator();
			while (rowIt.hasNext()) {
				org.apache.poi.ss.usermodel.Row row = rowIt.next();
				Iterator<org.apache.poi.ss.usermodel.Cell> cellIt = row.cellIterator();
				while (cellIt.hasNext()) {
					org.apache.poi.ss.usermodel.Cell cell = cellIt.next();
					System.out.print(cell.getStringCellValue() + "\t");
				}
				System.out.println();
			}
		}
		workBook.close();
	}

	public static void WriteXlsDoc(final String strFileName) {
		HSSFWorkbook workBook = new HSSFWorkbook();

		org.apache.poi.hssf.usermodel.HSSFFont font = workBook.createFont();
		font.setCharSet(HSSFFont.SYMBOL_CHARSET);
		font.setFontName("Courier New");
		// 斜体
		font.setItalic(true);
		// 字体大小
		font.setFontHeight((short) 300);
		font.setColor(IndexedColors.RED.index); // 字体颜色

		// 创建一个基本的样式
		org.apache.poi.hssf.usermodel.HSSFCellStyle cellStyle = workBook.createCellStyle();
		// 设置一个单元格边框颜色
		cellStyle.setBorderBottom(org.apache.poi.ss.usermodel.BorderStyle.DOUBLE);
		cellStyle.setBorderTop(org.apache.poi.ss.usermodel.BorderStyle.THIN);
		cellStyle.setBorderLeft(org.apache.poi.ss.usermodel.BorderStyle.DASH_DOT_DOT);
		cellStyle.setBorderRight(org.apache.poi.ss.usermodel.BorderStyle.MEDIUM_DASHED);
		// 设置一个单元格边框颜色
		cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		// 左右居中
		cellStyle.setAlignment(org.apache.poi.ss.usermodel.HorizontalAlignment.CENTER);
		// 上下居中
		cellStyle.setVerticalAlignment(org.apache.poi.ss.usermodel.VerticalAlignment.CENTER);
		cellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cellStyle.setFont(font);

		HSSFSheet sheet = workBook.createSheet();

		for (int r = 0; r < 30; r++) {
			HSSFRow row = sheet.createRow(r);
//			row.setHeight((short) 250);
			row.setHeightInPoints(40); // 行高
			for (int c = 0; c < 100; c++) {
				HSSFCell cell = row.createCell(c);
				cell.setCellValue("" + Math.pow(r, c));
				cell.setCellStyle(cellStyle);
			}
		}

		for (int c = 0; c < 50; c++) {
//			sheet.autoSizeColumn(c);// 自动调整列宽
			sheet.setColumnWidth(c, 4000);
		}

		// 合并单元格
		{
//			CellRangeAddress(int firstRow, int lastRow, int firstCol, int lastCol)
			sheet.addMergedRegion(new CellRangeAddress(31, 40, 5, 20));
			sheet.createRow(31).createCell(5).setCellValue("十八新娘八十郎，苍苍白发对红妆。\r\n" + 
					"鸳鸯被里成双夜，一树梨花压海棠。");
		}

		try {
			FileOutputStream out = new FileOutputStream(strFileName);
			try {
				workBook.write(out);
				out.close();
				workBook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void ReadXlsDoc(String strFileName) {
		try {
			FileInputStream fileIn = new FileInputStream(strFileName);
			HSSFWorkbook workbook;
			workbook = new HSSFWorkbook(fileIn);
			HSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<org.apache.poi.ss.usermodel.Row> rowIt = sheet.rowIterator();
			while (rowIt.hasNext()) {
				org.apache.poi.ss.usermodel.Row row = rowIt.next();
				Iterator<org.apache.poi.ss.usermodel.Cell> cellIt = row.cellIterator();
				while (cellIt.hasNext()) {
					org.apache.poi.ss.usermodel.Cell cell = (HSSFCell) cellIt.next();
					org.apache.poi.ss.usermodel.CellType cellType = cell.getCellType();
					if (cellType == org.apache.poi.ss.usermodel.CellType.NUMERIC) {
						System.out.print(cell.getNumericCellValue() + "\t");
					} else if (cellType == org.apache.poi.ss.usermodel.CellType.STRING) {
						System.out.print(cell.getStringCellValue() + "\t");
					} else {
						System.out.print(".....");
					}
				}
				System.out.println();
			}

			workbook.close();
			fileIn.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
