package ru.lumo.ssp.parser;

import ru.lumo.ssp.api.SpreadsheetParseException;
import ru.lumo.ssp.api.SpreadsheetParser;
import ru.lumo.ssp.api.SpreadsheetQueue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Abstract parser for any format
 *
 * <p>
 * Created by misha on 01.01.2017.
 */
public abstract class AbstractParser<R> implements SpreadsheetParser {

    protected int startRowIndex;

    private SpreadsheetQueue queue;

    @Override
    public void setStartRow(int index) {
        startRowIndex = index;
    }

    @Override
    public void setQueue(SpreadsheetQueue queue) {
        this.queue = queue;
    }

    protected List<List> parseLines(Iterator<R> it) throws SpreadsheetParseException {
        if (it.hasNext()) {
            List<List> list = new ArrayList<>();
            int lineIndex = startRowIndex;
            while (it.hasNext()) {
                list.add(parseLine(it.next(), lineIndex));
                lineIndex++;
            }
            return list;
        } else {
            post("ERROR! No input rows with data found");
            throw new SpreadsheetParseException("ERROR! No input rows with data found");
        }
    }

    protected abstract List parseLine(R row, int lineIndex) throws SpreadsheetParseException;

    protected void post(String message) {
        if (queue == null) return;
        queue.getQueue().add(message);
    }

}
