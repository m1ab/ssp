package ru.lumo.ssp.parser;

import ru.lumo.ssp.api.Spreadsheet;
import ru.lumo.ssp.api.SpreadsheetParseException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static ru.lumo.ssp.api.SpreadsheetFormat.csv;

/**
 * Parser for CSV text format
 *
 * <p>
 * Created by misha on 01.01.2017.
 */
@Spreadsheet(format = csv)
public class CsvParser extends AbstractParser<String> {

    private static final String DEFAULT_DELIMITER = ";";
    private static final String DEFAULT_DATE_PATTERN = "dd.MM.yyyy";
    private static final String DEFAULT_CHARSET = "UTF-8";

    private String delimiter;
    private DateFormat dateFormat;
    private String charset;
    private List<Class> config;

    public CsvParser() {
        this(DEFAULT_DELIMITER);
    }

    public CsvParser(String delimiter) {
        this(delimiter, DEFAULT_DATE_PATTERN);
    }

    public CsvParser(String delimiter, String datePattern) {
        this(delimiter, datePattern, DEFAULT_CHARSET);
    }

    public CsvParser(String delimiter, String datePattern, String charset) {
        this.delimiter = delimiter;
        this.dateFormat = new SimpleDateFormat(datePattern);
        this.charset = charset;
    }

    @Override
    public void setConfig(List<Class> config) {
        this.config = config;
    }

    @Override
    public Class getClass(int column) {
        return column >= config.size() ? String.class : config.get(column);
    }

    @Override
    public List<List> parse(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, charset));
        List<List> list = new ArrayList<>();
        int lineIndex = 0;
        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            list.add(parseLine(line, lineIndex));
            lineIndex++;
        }
        return list;
    }

    protected List parseLine(String line, int lineIndex) throws SpreadsheetParseException {
        if (line == null || line.isEmpty()) {
            post("ERROR! line " + (lineIndex + 1) + ", Input row object is null");
            throw new SpreadsheetParseException("ERROR! line " + (lineIndex + 1) + ", input row object is null");
        } else {
            try {
                String[] data = line.split(delimiter);
                post(Arrays.toString(data));
                List list = new ArrayList();
                int counter = 0;
                for (String s : data) {
                    list.add(parseObject(s, config.get(counter)));
                    counter++;
                }
                post(Arrays.toString(list.toArray()));
                return list;
            } catch (Exception e) {
                post("ERROR! line " + (lineIndex + 1) + ", " + e.getMessage());
                throw new SpreadsheetParseException("ERROR! line " + (lineIndex + 1) + ", " + e.getMessage(), e);
            }
        }
    }

    public Object parseObject(String s, Class cl) throws ParseException {
        if (s == null) return null;
        if (String.class.equals(cl)) {
            return s;
        } else if (Double.class.equals(cl)) {
            return Double.valueOf(s);
        } else if (Date.class.equals(cl)) {
            return dateFormat.parse(s);
        } else if (Boolean.class.equals(cl)) {
            return Boolean.valueOf(s);
        } else if (BigDecimal.class.equals(cl)) {
            return new BigDecimal(s);
        }
        return s;
    }
}
