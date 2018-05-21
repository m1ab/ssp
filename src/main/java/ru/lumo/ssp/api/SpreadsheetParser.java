package ru.lumo.ssp.api;

import java.io.InputStream;
import java.util.List;

/**
 * Spreadsheet parser interface
 *
 * <p>
 * Created by misha on 01.01.2017.
 */
public interface SpreadsheetParser {

    void setStartRow(int index);

    void setQueue(SpreadsheetQueue queue);

    void setConfig(List<Class> config);

    Class getClass(int column);

    List<List> parse(InputStream is) throws Exception;
}
