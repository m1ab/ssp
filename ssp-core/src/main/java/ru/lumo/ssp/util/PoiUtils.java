package ru.lumo.ssp.util;

import org.apache.poi.ss.usermodel.*;
import ru.lumo.ssp.api.SpreadsheetParseException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Parsing functions for poi library parsing
 *
 * <p>
 * Created by misha on 01.01.2017.
 */
public class PoiUtils {

//    public static Iterator<Row> iterator(Workbook workbook) throws Exception {
//        return iterator(workbook, 0);
//    }

    public static Iterator<Row> iterator(Workbook workbook, int sheetIndex) throws Exception {
        Sheet sheet = workbook.getSheetAt(sheetIndex);
        if (sheet == null) return null;
        return sheet.iterator();
    }

    public static List parseRow(Row row, List<Class> config) throws SpreadsheetParseException {
        int size = row.getLastCellNum();
        if (size < 0) return null;
        List list = new ArrayList(size);
        int counter = 0;
        for (Cell cell : row) {
            try {
                list.add(parseCell(cell, config.get(counter)));
                counter++;
            } catch (Exception e) {
                throw new SpreadsheetParseException(
                        "Cell " + (counter + 1) + " error: " + e.getMessage(), e);
            }
        }
        return list;
    }

    public static Object parseCell(Cell cell, Class cl) {
        if (cell == null) return null;
        if (String.class.equals(cl)) {
            cell.setCellType(CellType.STRING);
            return cell.getStringCellValue();
        } else if (Integer.class.equals(cl)) {
            cell.setCellType(CellType.NUMERIC);
            return (int)cell.getNumericCellValue();
        } else if (Long.class.equals(cl)) {
            cell.setCellType(CellType.NUMERIC);
            return (long)cell.getNumericCellValue();
        } else if (BigInteger.class.equals(cl)) {
            cell.setCellType(CellType.STRING);
            return new BigInteger(cell.getStringCellValue());
        } else if (Float.class.equals(cl)) {
            cell.setCellType(CellType.NUMERIC);
            return (float)cell.getNumericCellValue();
        } else if (Double.class.equals(cl)) {
            cell.setCellType(CellType.NUMERIC);
            return cell.getNumericCellValue();
        } else if (BigDecimal.class.equals(cl)) {
            cell.setCellType(CellType.STRING);
            return new BigDecimal(cell.getStringCellValue());
        } else if (Date.class.equals(cl)) {
            return cell.getDateCellValue();
        } else if (Boolean.class.equals(cl)) {
            cell.setCellType(CellType.BOOLEAN);
            return cell.getBooleanCellValue();
        }
        cell.setCellType(CellType.STRING);
        return cell.getStringCellValue();
    }
}
