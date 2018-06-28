package ru.lumo.ssp.parser;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import ru.lumo.ssp.api.Multisheet;
import ru.lumo.ssp.api.SpreadsheetParseException;
import ru.lumo.ssp.util.PoiUtils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Abstract parser for microsoft excel document format
 *
 * <p>
 * Created by misha on 01.01.2017.
 */
public abstract class AbstractPoiParser extends AbstractParser<Row> implements Multisheet {

    protected int sheetIndex = 0;

    private List<Class> config;

    @Override
    public void setConfig(List<Class> config) {
        this.config = config;
    }

    @Override
    public Class getClass(int column) {
        return column >= config.size() ? String.class : config.get(column);
    }

    protected Iterator<Row> getIterator(Workbook workbook) throws Exception {
        Iterator<Row> it = PoiUtils.iterator(workbook, sheetIndex);
        if (it == null) {
            post("ERROR! input rows not found");
            throw new SpreadsheetParseException("ERROR! input rows not found");
        }
        for (int i = 0; i < startRowIndex; i++) if (it.hasNext()) it.next(); // safe skip head (if exists we skip)
        return it;
    }

    protected List parseLine(Row row, int lineIndex) throws SpreadsheetParseException {
        if (row == null) {
            post("ERROR! line " + (lineIndex + 1) + ", Input row object is null");
            throw new SpreadsheetParseException("ERROR! line " + (lineIndex + 1) + ", input row object is null");
        } else {
            try {
                List data = PoiUtils.parseRow(row, config);
                if (data == null) {
                    post("ERROR! line " + (lineIndex + 1) + ", Parsed row data is null");
                    throw new SpreadsheetParseException("ERROR! line " + (lineIndex + 1) + ", parsed row data is null");
                }
                post(Arrays.toString(data.toArray()));
                return data;
            } catch (Exception e) {
                post("ERROR! line " + (lineIndex + 1) + ", " + e.getMessage());
                throw new SpreadsheetParseException("ERROR! line " + (lineIndex + 1) + ", " + e.getMessage(), e);
            }

        }
    }
//
//    public static List parseLine(Row row) {
//        int size = sheet.getLastRowNum();
//        if (row == null || row.getCellCount() < 0) return null;
//        List<List> list = new ArrayList<>(size);
//        for (Row row : sheet) {
//            list.add(parseRow(row));
//        }
//        return list;
//    }


    @Override
    public void setSheetIndex(int sheetIndex) {
        this.sheetIndex = sheetIndex;
    }
}
