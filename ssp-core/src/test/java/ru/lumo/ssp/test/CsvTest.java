package ru.lumo.ssp.test;

import org.junit.Test;
import ru.lumo.ssp.api.SpreadsheetFormat;
import ru.lumo.ssp.api.SpreadsheetParser;
import ru.lumo.ssp.parser.CsvParser;

import static ru.lumo.ssp.api.SpreadsheetFormat.csv;


public class CsvTest extends AbstractSpreadsheetTest {

    @Test
    @Override
    public void parse() throws Exception {
        super.parse();
    }

    @Override
    protected SpreadsheetFormat getFormat() {
        return csv;
    }

    @Override
    protected SpreadsheetParser initParser() {
        return new CsvParser();
    }
}
