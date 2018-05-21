package ru.lumo.ssp.parser;

import org.odftoolkit.simple.Document;
import org.odftoolkit.simple.table.Row;
import ru.lumo.ssp.api.Spreadsheet;
import ru.lumo.ssp.api.SpreadsheetParseException;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import static ru.lumo.ssp.api.SpreadsheetFormat.odt;

/**
 * Parser for ODT (Open Document Template) format
 *
 * <p>
 * Created by misha on 01.01.2017.
 */
@Spreadsheet(format = odt)
public class OdtParser extends AbstractOdfParser {

    @Override
    public List<List> parse(InputStream is) throws Exception {
        Document document = Document.loadDocument(is);
        Iterator<Row> it = getIterator(document);
        if (it == null) throw new SpreadsheetParseException("Document does not contains any table");
        return parseLines(it);
    }
}
