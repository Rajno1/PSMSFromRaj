package ExcelData;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {

	public String path;
	public FileInputStream fis;
	public FileOutputStream fos;
	private XSSFWorkbook wb;
	private XSSFSheet sheet;
	private XSSFRow row;
	private XSSFCell cell;
	public String celltext;
	private CellStyle style;

	public ExcelUtilities(String path) {
		this.path = path;
		try {
			fis = new FileInputStream(path);
			wb = new XSSFWorkbook(fis);
			wb.getSheetAt(0);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isSheetExist(String sheetName) {
		int index = wb.getSheetIndex(sheetName);
		if (index == -1) {
			index = wb.getSheetIndex(sheetName.toUpperCase());
			if (index == -1)
				return false;
			else
				return true;
		} else
			return true;
	}

	public int getRowCount(String sheetName) {
		int index = wb.getSheetIndex(sheetName);
		if (index ==-1) {
			return 0;
		} else {
			int rowcount = wb.getSheetAt(index).getLastRowNum();
			return rowcount;
		}
	}

	public int getColumnCount(String sheetName) {
		if (!isSheetExist(sheetName))
			return -1;
		sheet = wb.getSheet(sheetName);
		row = sheet.getRow(0);
		if (row == null)
			return -1;
		return row.getLastCellNum();
	}

	public boolean addSheet(String sheetName) {
		// it will return true if sheet got created successfully orelse it will return
		// false
		try {
			wb.createSheet(sheetName);
			fos = new FileOutputStream(path);
			wb.write(fos);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean removeSheet(String sheetName) {
		int index = wb.getSheetIndex(sheetName);
		if (index == -1)
			return false;
		try {
			wb.removeSheetAt(index);
			fos = new FileOutputStream(path);
			wb.write(fos);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean addColumn(String sheetName, String colname) {
		try {
			fis = new FileInputStream(path);
			wb = new XSSFWorkbook(fis);
			int index = wb.getSheetIndex(sheetName);
			if (index == -1)
				return false;
			XSSFCellStyle style = wb.createCellStyle();
			style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			sheet = wb.getSheetAt(index);
			row = sheet.getRow(0);
			if (row == null)
				row = sheet.createRow(0);
			if (row.getLastCellNum() == -1)
				cell = row.createCell(0);
			else
				cell = row.createCell(row.getLastCellNum());
			cell.setCellValue(colname);
			cell.setCellStyle(style);
			fos = new FileOutputStream(path);
			wb.write(fos);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean addColumnOutput(String Outputpath, String sheetName, String colname) {
		try {
			fis = new FileInputStream(Outputpath);
			wb = new XSSFWorkbook(fis);
			int index = wb.getSheetIndex(sheetName);
			if (index == -1)
				return false;
			XSSFCellStyle style = wb.createCellStyle();
			style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			sheet = wb.getSheetAt(index);
			row = sheet.getRow(0);
			if (row == null)
				row = sheet.createRow(0);
			if (row.getLastCellNum() == -1)
				cell = row.createCell(0);
			else
				cell = row.createCell(row.getLastCellNum());
			cell.setCellValue(colname);
			cell.setCellStyle(style);
			fos = new FileOutputStream(Outputpath);
			wb.write(fos);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean removeColumn(String sheetName, int colname) {
		try {
			if (!isSheetExist(sheetName))
				return false;
			fis = new FileInputStream(path);
			wb = new XSSFWorkbook(fis);
			sheet = wb.getSheet(sheetName);
			XSSFCellStyle style = wb.createCellStyle();
			//
			style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
			XSSFCreationHelper createHelper = wb.getCreationHelper();
			// style.setFillPattern(XSSFCellStyle.NO_FILL);
			for (int i = 0; i < getRowCount(sheetName); i++) {
				row = sheet.getRow(i);
				if (row != null) {
					cell = row.getCell(colname);
					if (cell != null) {
						cell.setCellStyle(style);
						row.removeCell(cell);
					}
				}
			}
			fos = new FileOutputStream(path);
			wb.write(fos);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public String getCellData(String sheetName, int rownum, int colnum) {
		try {
			if (rownum <= 0)
				return "";
			int index = wb.getSheetIndex(sheetName);
			if (index == -1)
				return "";
			sheet = wb.getSheetAt(index);
			row = sheet.getRow(rownum - 1);
			if (row == null)
				return "";
			cell = row.getCell(colnum);
			if (cell == null)
				return "";
			if (cell.getCellTypeEnum() == CellType.STRING)
				return cell.getStringCellValue();
			else if ((cell.getCellTypeEnum() == CellType.NUMERIC) || (cell.getCellTypeEnum() == CellType.FORMULA)) {
				String celltext = String.valueOf((int) cell.getNumericCellValue());
				if (DateUtil.isCellDateFormatted(cell)) {
					double d = cell.getNumericCellValue();
					Calendar cal = Calendar.getInstance();
					cal.setTime(DateUtil.getJavaDate(d));
					celltext = (String.valueOf(cal.get(Calendar.YEAR)));
					celltext = cal.get(Calendar.MONTH) + 1 + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + celltext;
				}
				return celltext;
			} else {
				cell.getCellTypeEnum();
				if (CellType.BLANK != null)
					return "";
				else
					return String.valueOf(cell.getBooleanCellValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "row " + rownum + " or column " + colnum + " does not exist in xls";
		}
	}

	public boolean setCellData(String sheetName, String colname, int rownum, String data) {
		try {
			fis = new FileInputStream(path);
			wb = new XSSFWorkbook(fis);
			if (rownum <= 0)
				return false;
			int index = wb.getSheetIndex(sheetName);
			int colnum = -1;
			if (index == -1)
				return false;
			sheet = wb.getSheetAt(index);
			row = sheet.getRow(0);
			if (data == "PASS") {
				style = wb.createCellStyle();
				style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
				style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				Font font = wb.createFont();
				font.setColor(IndexedColors.BLACK.getIndex());
				style.setFont(font);

			} else if (data == "FAIL") {
				style = wb.createCellStyle();
				style.setFillForegroundColor(IndexedColors.RED.getIndex());
				style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				Font font = wb.createFont();
				font.setColor(IndexedColors.BLACK.getIndex());
				style.setFont(font);
			} else {
				style = wb.createCellStyle();
				style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
				style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				Font font = wb.createFont();
				font.setColor(IndexedColors.BLACK.getIndex());
				style.setFont(font);
			}

			for (int i = 0; i < row.getLastCellNum(); i++) {
				// System.out.println(row.getCell(i).getStringCellValue().trim());
				if (row.getCell(i).getStringCellValue().trim().equals(colname))
					colnum = i;
			}
			if (colnum == -1)
				return false;
			sheet.autoSizeColumn(colnum);
			row = sheet.getRow(rownum - 1);
			if (row == null)
				row = sheet.createRow(rownum - 1);
			cell = row.getCell(colnum);
			if (cell == null)
				cell = row.createCell(colnum);
			// cell style
			// CellStyle cs = workbook.createCellStyle();
			// cs.setWrapText(true);
			// cell.setCellStyle(cs);
			cell.setCellValue(data);
			cell.setCellStyle(style);
			fos = new FileOutputStream(path);
			wb.write(fos);
			fos.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public String getCellDataInput(String sheetName, String colname, int rownum) {

		try {

			if (rownum <= 0)

				return "";

			int index = wb.getSheetIndex(sheetName);

			int col_num = -1;

			if (index == -1)

				return "";

			sheet = wb.getSheetAt(index);

			row = sheet.getRow(0);

			for (int i = 0; i < row.getLastCellNum(); i++) {

				// System.out.println(row.getCell(i).getStringCellValue().trim());

				if (row.getCell(i).getStringCellValue().trim().equals(colname.trim()))

					col_num = i;
			}

			if (col_num == -1)

				return "";

			sheet = wb.getSheetAt(index);

			row = sheet.getRow(rownum - 1);

			if (row == null)

				return "";

			cell = row.getCell(col_num);

			if (cell == null)

				return "";

			// System.out.println(cell.getCellType().name());

			if (cell.getCellTypeEnum() == CellType.STRING)

				return cell.getStringCellValue();

			// if (cell.getCellType().STRING != null)

			// if(cell.getCellType()==Xls_Reader.CELL_TYPE_STRING)

			// return cell.getStringCellValue();

			else if ((cell.getCellTypeEnum() == CellType.NUMERIC) || (cell.getCellTypeEnum() == CellType.FORMULA)) {

				String cellText = String.valueOf((int) cell.getNumericCellValue());

				if (DateUtil.isCellDateFormatted(cell)) {

					// format in form of M/D/YY

					double d = cell.getNumericCellValue();

					Calendar cal = Calendar.getInstance();

					cal.setTime(DateUtil.getJavaDate(d));

					cellText = (String.valueOf(cal.get(Calendar.YEAR)));

					cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + 1 + "/" + cellText;

					// System.out.println(cellText);

				}

				return cellText;

			} else {
				cell.getCellTypeEnum();
				if (CellType.BLANK != null)

					return "";

				else

					return String.valueOf(cell.getBooleanCellValue());
			}

		} catch (Exception e) {

			e.printStackTrace();

			return "row " + rownum + " or column " + colname + " does not exist in xls";

		}

	}

	public boolean setCellDataOutput(String path1, String sheetName, int rownum, String colname, String data) {

		try {

			fis = new FileInputStream(path1);

			wb = new XSSFWorkbook(fis);

			if (rownum <= 0)

				return false;

			int index = wb.getSheetIndex(sheetName);

			int colnum = -1;

			if (index == -1)

				return false;

			sheet = wb.getSheetAt(index);

			row = sheet.getRow(0);
			if (data == "PASS") {
				style = wb.createCellStyle();
				style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
				style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				Font font = wb.createFont();
				font.setColor(IndexedColors.BLACK.getIndex());
				style.setFont(font);

			} else if (data == "FAIL") {
				style = wb.createCellStyle();
				style.setFillForegroundColor(IndexedColors.RED.getIndex());
				style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				Font font = wb.createFont();
				font.setColor(IndexedColors.BLACK.getIndex());
				style.setFont(font);
			} else {
				style = wb.createCellStyle();
				style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
				style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				Font font = wb.createFont();
				font.setColor(IndexedColors.BLACK.getIndex());
				style.setFont(font);
			}
			for (int i = 0; i < row.getLastCellNum(); i++) {

				// System.out.println(row.getCell(i).getStringCellValue().trim());

				if (row.getCell(i).getStringCellValue().trim().equals(colname))

					colnum = i;
			}

			if (colnum == -1)

				return false;

			sheet.autoSizeColumn(colnum);

			row = sheet.getRow(rownum - 1);

			if (row == null)

				row = sheet.createRow(rownum - 1);

			cell = row.getCell(colnum);

			if (cell == null)

				cell = row.createCell(colnum);

			// cell style

			// CellStyle cs = workbook.createCellStyle();

			// cs.setWrapText(true);

			// cell.setCellStyle(cs);

			cell.setCellValue(data);
			cell.setCellStyle(style);
			fos = new FileOutputStream(path1);

			wb.write(fos);
			fos.flush();
			fos.close();

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
		return true;
	}
}
