package ExcelReader;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {
    private Workbook workbook;

    // Constructor to load the Excel file
    public ExcelReader(String filePath) throws IOException {
        FileInputStream file = new FileInputStream(new File(filePath));
        workbook = new XSSFWorkbook(file);
        file.close();
    }

    // Method to read the entire Excel sheet
    public static List<List<String>> readExcel(String filePath) throws IOException {
        List<List<String>> data = new ArrayList<>();
        FileInputStream file = new FileInputStream(new File(filePath));
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            List<String> rowData = new ArrayList<>();
            for (Cell cell : row) {
                rowData.add(cell.toString()); // Convert all cell data to String
            }
            data.add(rowData);
        }

        workbook.close();
        file.close();
        return data;
    }

    // Method to get cell data by sheet name, column index, and row index
    public String getCellData(String sheetName, int row, int col) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            throw new IllegalArgumentException("Sheet not found: " + sheetName);
        }

        Row sheetRow = sheet.getRow(row);
        if (sheetRow == null) {
            return ""; // Return empty if row is missing
        }

        Cell cell = sheetRow.getCell(col);
        if (cell == null) {
            return ""; // Return empty if cell is missing
        }

        return cell.toString(); // Convert cell value to string
    }

    // Method to get the number of columns in a sheet
    public int getColumnCount(String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            throw new IllegalArgumentException("Sheet not found: " + sheetName);
        }

        Row firstRow = sheet.getRow(0);
        return (firstRow == null) ? 0 : firstRow.getPhysicalNumberOfCells(); // Count columns in the first row
    }

    // Method to get the number of rows in a sheet
    public int getRowCount(String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            throw new IllegalArgumentException("Sheet not found: " + sheetName);
        }

        return sheet.getPhysicalNumberOfRows(); // Count all rows
    }

    // Close the workbook
    public void close() throws IOException {
        if (workbook != null) {
            workbook.close();
        }
    }

    public void printSheetNames() {
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            System.out.println("Sheet " + i + ": " + workbook.getSheetName(i));
        }
    }

}
