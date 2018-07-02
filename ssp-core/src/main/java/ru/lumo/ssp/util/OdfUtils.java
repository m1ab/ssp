package ru.lumo.ssp.util;

import org.odftoolkit.simple.Document;
import org.odftoolkit.simple.table.Cell;
import org.odftoolkit.simple.table.Row;
import org.odftoolkit.simple.table.Table;
import ru.lumo.ssp.api.SpreadsheetParseException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Parsing functions for open document format
 *
 * <p>
 * Created by misha on 01.01.2017.
 */
public class OdfUtils {

//    public static Iterator<Row> iterator(Document document) throws Exception {
//        return iterator(document, 0);
//    }

    public static Iterator<Row> iterator(Document document, int sheetIndex) throws Exception {
        List<Table> tableList = document.getTableList();
        if (tableList == null || tableList.isEmpty() || tableList.get(sheetIndex) == null) return null;
        return tableList.get(sheetIndex).getRowIterator();
    }

    public static List parseRow(Row row, List<Class> config) throws SpreadsheetParseException {
        List list = new ArrayList();
        for (int i = 0; i < row.getCellCount(); i++) {
            try {
                list.add(parseCell(row.getCellByIndex(i), config.get(i)));
            } catch (Exception e) {
                throw new SpreadsheetParseException(
                        "Cell " + (i + 1) + " error: " + e.getMessage(), e);
            }
        }
        return list;
    }

    public static Object parseCell(Cell cell, Class cl) {
        if (cell == null) return null;
        if (String.class.equals(cl)) {
            return cell.getStringValue();
        } else if (Integer.class.equals(cl)) {
            return cell.getDoubleValue();
        } else if (Double.class.equals(cl)) {
            return cell.getDoubleValue();
        } else if (Date.class.equals(cl)) {
            return cell.getDateValue();
        } else if (Boolean.class.equals(cl)) {
            return cell.getBooleanValue();
        } else if (BigDecimal.class.equals(cl)) {
            return cell.getCurrencyValue();
        }
        return cell.getStringValue();
    }
}
