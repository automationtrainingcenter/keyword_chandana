package utitlities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelHelper {
	Workbook book;
	Sheet sh;
	WritableWorkbook wbook;
	WritableSheet wsh;
	Label label;

	private String getFilePath(String folderName, String fileName) {
		return System.getProperty("user.dir") + File.separator + folderName + File.separator + fileName;
	}

	// open an excel file to read the data
	public void openExcel(String folderName, String fileName, String sheetName) {
		// create an object of FileInputStream class by providing file path
		FileInputStream fis;
		try {
			fis = new FileInputStream(getFilePath(folderName, fileName));
			// create Workbook object
			book = Workbook.getWorkbook(fis);
			// create sheet object
			sh = book.getSheet(sheetName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// find number of rows
	public int rowCount() {
		return sh.getRows();
	}

	// find number of columns
	public int columnCount() {
		return sh.getColumns();
	}

	// read data from a cell
	public String readData(int rnum, int cnum) {
		return sh.getCell(cnum, rnum).getContents();
	}

	// open or create an excel file to write the data
	public void createExcel(String folderName, String iFileName, String oFileName, String sheetName) {
		try {
			FileInputStream fis = new FileInputStream(getFilePath(folderName, iFileName));
			// create Workbook object
			book = Workbook.getWorkbook(fis);
			// create FileOutputStream class object
			FileOutputStream fos = new FileOutputStream(getFilePath(folderName, oFileName));
			// create WritableWorkbook object
			wbook = Workbook.createWorkbook(fos, book);
			wsh = wbook.getSheet(sheetName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// write the data into the cell
	public void writeData(int rnum, int cnum, String data) {
		try {
			wsh.addCell(new Label(cnum, rnum, data));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// save and close work book
	public void closeBook() {
		try {
			wbook.write();
			wbook.close();
			book.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		ExcelHelper e1 = new ExcelHelper();
		e1.openExcel("", "input.xls", "test");
		e1.createExcel("", "input.xls", "input.xls", "test");
		int nor = e1.rowCount();
		for (int i = 1; i < nor; i++) {
			int sum = Integer.parseInt(e1.readData(i, 0)) + Integer.parseInt(e1.readData(i, 1));
			e1.writeData(i, 2, Integer.toString(sum));
		}
		e1.closeBook();
	}
}
