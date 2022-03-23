package datadriventesting;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {

	public static FileInputStream fileInput;
	public static FileOutputStream fileOutput;
	public static XSSFWorkbook workBook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static String path = null;

	public static int getRowCount(String path, String sheetName) throws Exception {
		int rowCount = 0;
		if(path.length()==0) {
			throw new FileNotFoundException(path + "is not found");
		}
		fileInput = new FileInputStream(path);
		workBook = new XSSFWorkbook(fileInput);

		try {
			if(sheetName.length()!=0) {
				sheet = workBook.getSheet(sheetName);
				rowCount = sheet.getLastRowNum();
			}else {
				rowCount=-1;
			}		
		}catch (Exception e) {
			e.getMessage();
		}finally {
			workBook.close();
			fileInput.close();
		}

		return rowCount;
	}

	public static int getCellCount(String path,String sheetName, int rowNum) throws IOException {
		int cellCount = 0;
		if(path.length()==0) {
			throw new FileNotFoundException(path + "is not found");
		}

		fileInput = new FileInputStream(path);
		workBook = new XSSFWorkbook(fileInput);
		try {
			if(sheetName.length()!=0) {
				sheet = workBook.getSheet(sheetName);
				row = sheet.getRow(rowNum);
				cellCount = row.getLastCellNum();
			}else {
				cellCount=-1;
			}		
		}catch (Exception e) {
			e.getMessage();
		}finally {
			workBook.close();
			fileInput.close();
		}
		return cellCount;

	}

	public static String getCellData(String path,String sheetName, int rowNum, int cellNum) throws IOException {
		String data = null;
		if(path.length()==0) {
			throw new FileNotFoundException(path + "is not found");
		}

		fileInput = new FileInputStream(path);
		workBook = new XSSFWorkbook(fileInput);
		try {
			if(sheetName.length()!=0) {
				sheet = workBook.getSheet(sheetName);
				row = sheet.getRow(rowNum);
				cell = row.getCell(cellNum);
				if(cell!=null) {
					DataFormatter formatter = new DataFormatter();
					try {
						data = formatter.formatCellValue(cell);
					} catch (Exception e) {
						data = "";
					}
				}else {
					data = null;
				}
			}	
		}catch (Exception e) {
			e.getMessage();
		}finally {
			workBook.close();
			fileInput.close();
		}
		System.out.println(data);
		return data;
	}
	
	public static void setCellData(String path,String sheetName, int rowNum, int colNum, String data) throws IOException {
		
		if(path.length()==0) {
			throw new FileNotFoundException(path + "is not found");
		}

		fileInput = new FileInputStream(path);
		workBook = new XSSFWorkbook(fileInput);
		
		sheet = workBook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cell = row.createCell(colNum);
		cell.setCellValue(data);
		
		fileOutput = new FileOutputStream(path);
		workBook.write(fileOutput);
		
		workBook.close();
		fileInput.close();
		fileOutput.close();
	}

	public static void main(String[] args) throws Exception {
		XLUtility.setCellData("D:\\Automation\\RestassuredAutomation\\src\\test\\java\\datadriventesting\\TestData.xlsx", "Sheet1",1,4,"ttttt");
	}
}
