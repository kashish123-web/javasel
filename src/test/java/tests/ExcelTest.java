package tests;


import org.testng.annotations.Test;
import utils.ExcelUtils;

public class ExcelTest {

	@Test
    public void readExcelData() {
//        String data = ExcelUtils.getCellData("Sheet1", 1, 4);
//        System.out.println("Excel Data: " + data);
	    ExcelUtils.printAllData("Sheet1");

		
		
    }

}
