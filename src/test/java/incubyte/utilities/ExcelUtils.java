package incubyte.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;

	/**
	 * 
	 * @param workBookPath - Absolute path of the Workbook/Excel file.
	 * @param sheetName    - Name of the sheet in given workbook
	 * @return XSSFSheet object, obtained using given sheet name.
	 */
	public static XSSFSheet getSheetBySheetName(String workBookPath, String sheetName) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(workBookPath);
		} catch (FileNotFoundException e) {
			System.out.println("No Excel file available on given path, '" + workBookPath
					+ "'. Please verify and provide the correct path.");
			e.printStackTrace();
		}  catch (Exception e) {
			System.out.println("Some unexpected error occured. Please verify.");
			e.printStackTrace();
		}

		try {
			workbook = new XSSFWorkbook(fis);
			System.out.println("Workbook object is created.");
		} catch (NullPointerException e) {
			System.out.println("NullPointerException occured while reading the excel file. Please check.");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error occured while reading the Excel file.");
			e.printStackTrace();
		}

		try {
			sheet = workbook.getSheet(sheetName);
		} catch (NullPointerException e) {
			System.out.println("NullPointerException occured while getting the sheet name. Please check.");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("No sheet available with name '" + sheetName + "' in the execel file. Please verify.");
			e.printStackTrace();

		}
		return sheet;
	}

	/**
	 * 
	 * @param workBookPath - Absolute path of the Workbook/Excel file.
	 * @param sheetName    - Index of the sheet(starting from 0) in given workbook.
	 * @return XSSFSheet object, obtained using given sheet index.
	 */
	public static XSSFSheet getSheetByIndex(String workBookPath, int sheetIndex) {
		FileInputStream fis = null;
		//OPCPackage opc = null;
		try {
			 fis = new FileInputStream(new File(workBookPath));
			// fis = new FileInputStream(workBookPath);
			
		} catch (FileNotFoundException e) {
			System.out.println("No Excel file available on given path, '" + workBookPath
					+ "'. Please verify and provide the correct path.");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Some unexpected error occured. Please verify.");
			e.printStackTrace();
		}
		
		try {
			if (fis != null) {
				workbook = new XSSFWorkbook(fis);
				System.out.println("Workbook object is created.");
			}
			else
			{
				System.out.println("FinleInputStream object is null.");
			}
		} catch (NullPointerException e) {
			System.out.println("NullPointerException occured while reading the excel file. Please check.");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error occured while reading the Excel file.");
			e.printStackTrace();
		}

		try {
			sheet = workbook.getSheetAt(sheetIndex);
			System.out.println("Sheet object is created.");
		} catch (NullPointerException e) {
			System.out.println("NullPointerException occured while getting the sheet name. Please check.");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("No sheet available with on index '" + sheetIndex + "' in the execel file. Please verify.");
			e.printStackTrace();
		}
		return sheet;
	}

	/**
	 * 
	 * @param sheetObject - XSSFSheet object.
	 * @return the count of Rows in the sheet.
	 */
	public static int getRowsCountInSheet(XSSFSheet sheetObject) {
		int rowNumbers = 0;
		rowNumbers = sheetObject.getLastRowNum();
		System.out.println(rowNumbers+" rows present in the selected sheet. Returning to the calling method.");
		return rowNumbers;
	}

	/**
	 * 
	 * * @param sheetObject - XSSFSheet object.
	 * 
	 * @return the count of columns in the sheet.
	 */
	public static int getColumnCountInSheet(XSSFSheet sheetObject) {
		int columnNumbers = 0;
		columnNumbers = sheetObject.getRow(0).getLastCellNum();
		System.out.println(columnNumbers+" columns present in the selwcted sheet. Returning to the calling method.");
		return columnNumbers;
	}

	public static String getCellData(XSSFSheet sheetObject, int rowNum, int colNum) {
		System.out.println("Reading the cell data.");
		String result = "";
		XSSFRow row = sheetObject.getRow(rowNum);
		XSSFCell cell = row.getCell(colNum);
		DataFormatter df = new DataFormatter();
		result = df.formatCellValue(cell);
		System.out.println("Cell value is "+result);
		/*if (cell.getCellType() == CellType.STRING) {
			result = cell.getStringCellValue();
		} else if (cell.getCellType() == CellType.NUMERIC) {
			result = String.valueOf(cell.getNumericCellValue());
		} else {
			result = cell.getStringCellValue();
		}*/
		return result;
	}
}

