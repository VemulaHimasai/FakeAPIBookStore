package api.utilities;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtilityBook {

	 public static Object[][] getTestData(String filePath, String sheetName) {
		 try {
	            FileInputStream fis = new FileInputStream(filePath);
	            XSSFWorkbook workbook = new XSSFWorkbook(fis);
	            XSSFSheet sheet = workbook.getSheet(sheetName);

	            int rowCount = sheet.getPhysicalNumberOfRows();
	            int colCount = sheet.getRow(0).getLastCellNum();

	            Object[][] data = new Object[rowCount - 1][colCount];

	            for (int i = 1; i < rowCount; i++) {
	                for (int j = 0; j < colCount; j++) {
	                    data[i - 1][j] = sheet.getRow(i).getCell(j).toString();
	                }
	            }

	            workbook.close();
	            fis.close();
	            return data;
		 }catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	 }
}
