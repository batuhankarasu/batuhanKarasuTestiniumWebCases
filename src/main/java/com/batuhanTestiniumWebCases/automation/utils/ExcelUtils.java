package com.batuhanTestiniumWebCases.automation.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;

public final class ExcelUtils {
    static final Logger logger = LogManager.getLogger(ExcelUtils.class);


    public static String readCell(String resourcePath, int sheetIndex, int rowIdx, int colIdx) {
        logger.info("Trying to read Excel file from classpath: " + resourcePath);

        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourcePath)) {

            if (is == null) {
                logger.error("Resource not found in classpath: " + resourcePath);
                throw new IllegalArgumentException("Resource not found in classpath: " + resourcePath +
                        " | Make sure the file exists in src/main/resources or is in the classpath.");
            }

            try (Workbook workbook = new XSSFWorkbook(is)) {
                Sheet sheet = workbook.getSheetAt(sheetIndex);
                Row row = (sheet != null) ? sheet.getRow(rowIdx) : null;
                Cell cell = (row != null) ? row.getCell(colIdx) : null;

                String value = (cell != null) ? new DataFormatter().formatCellValue(cell).trim() : "";
                logger.info("Read value '" + value + "' from " + resourcePath +
                        " (sheet:" + sheetIndex + ", R" + rowIdx + "C" + colIdx + ")");
                return value;

            }

        } catch (Exception e) {
            logger.error("Failed to read Excel cell from " + resourcePath +
                    " (sheet:" + sheetIndex + ", R" + rowIdx + "C" + colIdx + ")", e);
            throw new RuntimeException(e);
        }
    }

}
