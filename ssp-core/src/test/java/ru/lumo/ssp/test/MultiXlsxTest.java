package ru.lumo.ssp.test;

import org.junit.Test;
import ru.lumo.ssp.api.SpreadsheetFormat;
import ru.lumo.ssp.api.SpreadsheetParser;
import ru.lumo.ssp.parser.XlsxParser;

import static ru.lumo.ssp.api.SpreadsheetFormat.xlsx;


public class MultiXlsxTest extends AbstractSpreadsheetTest {

    @Test
    @Override
    public void parse() throws Exception {
        super.parse();
    }

    @Override
    protected SpreadsheetFormat getFormat() {
        return xlsx;
    }

    @Override
    protected SpreadsheetParser initParser() {
        XlsxParser parser = new XlsxParser();
        parser.setSheetIndex(1);
        return parser;
    }

    @Override
    protected String getResource() {
        return "multisheet";
    }
}
