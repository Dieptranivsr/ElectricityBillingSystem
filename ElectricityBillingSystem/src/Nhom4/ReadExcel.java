/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nhom4;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

/**
 *
 * @author Dieptuantran
 */
public class ReadExcel {
    public static void main(String[] args) throws IOException {
        File excelFile = new File("D:/Kihoc20202/LapTrinhNangCao/hoadon/books.xlsx");
        FileInputStream fls = new FileInputStream(excelFile);
        
        // Get workbook
        
        XSSFWorkbook workbook = new XSSFWorkbook(fls);
        XSSFSheet sheet = workbook.getSheetAt(0);
        
        Iterator<Row> rowIt = sheet.iterator();
        
        while (rowIt.hasNext()) {
            Row row = rowIt.next();
            
            Iterator<Cell> cellIterator = row.cellIterator();
            
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                System.out.println(cell.toString()+";");
            }
            System.out.println("");
        }
        workbook.close();
        fls.close();
    }
}
