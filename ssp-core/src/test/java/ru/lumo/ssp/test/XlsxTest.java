package ru.lumo.ssp.test;

import org.junit.Test;
import ru.lumo.ssp.api.SpreadsheetFormat;
import ru.lumo.ssp.api.SpreadsheetParser;
import ru.lumo.ssp.parser.XlsxParser;

import static ru.lumo.ssp.api.SpreadsheetFormat.xlsx;


public class XlsxTest extends AbstractSpreadsheetTest {

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
        return new XlsxParser();
    }
}
