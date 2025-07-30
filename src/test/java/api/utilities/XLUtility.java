package api.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFEvaluationWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {
	
	public static Object[][] getTestData(String filePath, String sheetName){
		try(FileInputStream file = new FileInputStream(filePath);
				Workbook workbook= new XSSFWorkbook(file)) {
			
			Sheet sheet = workbook.getSheet(sheetName);
			int rowCount = sheet.getPhysicalNumberOfRows() - 1;
			int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
			
			Object[][] data = new Object[rowCount][colCount];
			
			 for (int i = 1; i <= rowCount; i++) {
	                Row row = sheet.getRow(i);
	                for (int j = 0; j < colCount; j++) {
	                	Cell cell = row != null ? row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK):null;
	                    data[i - 1][j] = getCellValue(row.getCell(j));
	                }
	            }
			 return data;
			
			
			
		}catch(IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static Object getCellValue(Cell cell) {
		
		if(cell == null) {
			return "";
		}
		switch (cell.getCellType()) {
	    case STRING:
	        return cell.getStringCellValue();
	    case NUMERIC:
	        return (int) cell.getNumericCellValue();
	    case BOOLEAN:
	        return cell.getBooleanCellValue();
	    default:
	        return null;
	}
	}

}
