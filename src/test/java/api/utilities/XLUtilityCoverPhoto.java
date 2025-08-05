package api.utilities;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtilityCoverPhoto {
	
	public static Object[][] getTestData(String filePath,String sheetName){
		try {
			FileInputStream fis = new FileInputStream(filePath);
			Workbook wb = new XSSFWorkbook(fis);
			Sheet sheet = wb.getSheet(sheetName);
			int rows = sheet.getPhysicalNumberOfRows();
			int cols = sheet.getRow(0).getLastCellNum();
			
			Object[][] data = new Object[rows - 1][cols];
			
			for(int i = 1; i< rows; i++) {
				for (int j = 0; j < cols; j++) {
					sheet.getRow(i).getCell(j).setCellType(CellType.STRING);
					data[i - 1][j] = sheet.getRow(i).getCell(j).getStringCellValue();
				}
			}
			wb.close();
			fis.close();
			return data;
			
		}catch(Exception e) {
			e.printStackTrace();
            return null;
		}
	}
}
