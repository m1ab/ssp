package ru.lumo.ssp.parser;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.lumo.ssp.api.Spreadsheet;
import ru.lumo.ssp.api.SpreadsheetParseException;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import static ru.lumo.ssp.api.SpreadsheetFormat.xlsx;

/**
 * Parser for XLSX format
 *
 * <p>
 * Created by misha on 01.01.2017.
 */
@Spreadsheet(format = xlsx)
public class XlsxParser extends AbstractPoiParser {

    @Override
    public List<List> parse(InputStream is) throws Exception {
//        OPCPackage pkg = OPCPackage.open(is);
//        Workbook workbook = new SXSSFWorkbook(new XSSFWorkbook(pkg), 100);
        Workbook workbook = new XSSFWorkbook(is);
        Iterator<Row> it =  getIterator(workbook);
        if (it == null) throw new SpreadsheetParseException("Workbook does not contains any sheets");
        return parseLines(it);
    }


}
