package dealingExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile {

	int columnIndex = 0;

	public int writeExcel(String filepath, String filename, String sheetname)
			throws IOException {

		File file = new File(filepath + "\\" + filename);

		FileInputStream inputstream = new FileInputStream(file);

		Workbook wbk = null;

		String fileExtentionName = filename.substring(filename.indexOf("."));

		if (fileExtentionName.equalsIgnoreCase(".xlsx")) {
			wbk = new XSSFWorkbook(inputstream);
		} else if (fileExtentionName.equalsIgnoreCase(".xls")) {
			wbk = new HSSFWorkbook(inputstream);
		}

		Sheet sh = wbk.getSheet(sheetname);
		int rowCount = sh.getLastRowNum() - sh.getFirstRowNum();

		for (int i = 0; i < rowCount + 1; i++) {

			Row row = sh.getRow(i);

			for (int j = 0; j < row.getLastCellNum(); j++) {

				// Cell cell = row.createCell(j);

				if (row.getCell(j)!=null && row.getCell(j).getStringCellValue()
						.equalsIgnoreCase("Student_ID")) {

					columnIndex = row.getCell(j).getColumnIndex();
					break;
				}

			}

		}
		
		return columnIndex;
	}

	public static void main(String[] args) throws IOException {
		
		ReadExcelFile rd=new ReadExcelFile();

		int a=rd.writeExcel("D:\\New folder", "test.xlsx", "Sheet1");
		
		System.out.println(a);

	}

}
