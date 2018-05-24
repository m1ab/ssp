package ru.lumo.ssp.test;

import org.junit.Test;
import ru.lumo.ssp.api.SpreadsheetFormat;
import ru.lumo.ssp.api.SpreadsheetParser;
import ru.lumo.ssp.parser.XlsParser;

import static ru.lumo.ssp.api.SpreadsheetFormat.xls;

public class XlsTest extends AbstractSpreadsheetTest {

    @Test
    @Override
    public void parse() throws Exception {
        super.parse();
    }

    @Override
    protected SpreadsheetFormat getFormat() {
        return xls;
    }

    @Override
    protected SpreadsheetParser initParser() {
        return new XlsParser();
    }
}
