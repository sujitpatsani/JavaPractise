package dealingExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelIterator {
	
	public int getCellIndex(String filepath, String filename, String sheetname,String headername) throws IOException{
		
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
		
		 Iterator rows = sh.rowIterator();
		
	
		
		
		
		
		
		return 0;
		
	}

}
