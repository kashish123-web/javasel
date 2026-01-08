package utils;

import java.io.InputStream;
import org.apache.poi.ss.usermodel.*;

public class ExcelUtils {

//    public static String getCellData(String sheetName, int rowNum, int colNum) {
//        try {
//            InputStream is = ExcelUtils.class
//                    .getClassLoader()
//                    .getResourceAsStream("testdata/TestData.xls");
//
//            Workbook workbook = WorkbookFactory.create(is);
//            Sheet sheet = workbook.getSheet(sheetName);
//            Cell cell = sheet.getRow(rowNum).getCell(colNum);
//
//            DataFormatter formatter = new DataFormatter();
//            return formatter.formatCellValue(cell);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "";
//        }
//    }
	
	
	public static void printAllData(String sheetName) {
	    try {
	        InputStream is = ExcelUtils.class
	                .getClassLoader()
	                .getResourceAsStream("testdata/TestData.xls");

	        Workbook workbook = WorkbookFactory.create(is);
	        Sheet sheet = workbook.getSheet(sheetName);

	        DataFormatter formatter = new DataFormatter();

	        for (Row row : sheet) {
	            for (Cell cell : row) {
	                System.out.print(formatter.formatCellValue(cell) + "\t");
	            }
	            System.out.println();
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
