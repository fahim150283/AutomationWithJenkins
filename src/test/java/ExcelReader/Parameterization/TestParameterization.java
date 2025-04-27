package ExcelReader.Parameterization;

import ExcelReader.ExcelReader;
import Utilities.Setup;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Hashtable;

public abstract class TestParameterization extends Setup {
    private static ExcelReader excelReader;

    @BeforeClass
    public void setup() throws IOException {
        // Initialize ExcelReader with file path
        excelReader = new ExcelReader("src/test/resources/LoginTest.xlsx");
    }

    @Test(dataProvider = "getData")
    public void DoLogin(Hashtable<String, String> data) {
        String username = data.get("username");
        String password = data.get("password");
        String isCorrect = data.get("isCorrect");
        String fart = data.get("fart");
        String dumb = data.get("dumb");


        fluentwait.until(
                d -> {
                    System.out.println("Username: " + username + " | Password: " + password + " | isCorrect: " + isCorrect + " | fart: " + fart + " | dumb: " + dumb);
                    return true;
                });
    }

    @DataProvider
    public Object[][] getData() {
        String sheetname = "Sheet1";

        int rows = excelReader.getRowCount(sheetname);
        int cols = excelReader.getColumnCount(sheetname);
        Hashtable<String, String> table = null;
        Object[][] data = new Object[rows - 1][1]; // Ignoring headers

        for (int row = 1; row < rows; row++) { // Start from 1 (skip header)
            table = new Hashtable<String, String>();
            for (int col = 0; col < cols; col++) {
//                data[row - 1][col] = excelReader.getCellData(sheetname, row, col);
                table.put(excelReader.getCellData(sheetname, 0, col), excelReader.getCellData(sheetname, row, col));
                data[row - 1][0] = table;
            }
        }
        return data;
    }
}
