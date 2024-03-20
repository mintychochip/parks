package org.example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.container.Park;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class NewWorkBook {
    private final Workbook workbook;

    private final String writeFileName;

    public NewWorkBook(String writeFileName) {
        this.workbook = new XSSFWorkbook();
        this.writeFileName = writeFileName;
    }
    public Sheet createSheet(String name, String[] headers) {
        Sheet sheet = workbook.createSheet(name);
        Row row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            row.createCell(i).setCellValue(headers[i]);
        }
        return sheet;
    }

    public Workbook getWorkbook() {
        return workbook;
    }

    public void write() {
        try {
            OutputStream out = new FileOutputStream(this.writeFileName);
            workbook.write(out);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
