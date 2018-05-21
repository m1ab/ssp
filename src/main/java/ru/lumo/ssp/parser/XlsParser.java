package ru.lumo.ssp.parser;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import ru.lumo.ssp.api.Spreadsheet;
import ru.lumo.ssp.api.SpreadsheetParseException;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import static ru.lumo.ssp.api.SpreadsheetFormat.xls;

/**
 * Parser for XLS format
 *
 * <p>
 * Created by misha on 01.01.2017.
 */
@Spreadsheet(format = xls)
public class XlsParser extends AbstractPoiParser {

    @Override
    public List<List> parse(InputStream is) throws Exception {
        Workbook workbook = new HSSFWorkbook(is);
        Iterator<Row> it =  getIterator(workbook);
        if (it == null) throw new SpreadsheetParseException("Workbook does not contains any sheets");
        return parseLines(it);
    }


}
