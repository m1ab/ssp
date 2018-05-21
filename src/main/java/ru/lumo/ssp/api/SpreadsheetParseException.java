package ru.lumo.ssp.api;

/**
 * Spreadsheet parse data exception
 *
 * <p>
 * Created by misha on 01.01.2017.
 */
public class SpreadsheetParseException extends Exception {

    public SpreadsheetParseException() {
    }

    public SpreadsheetParseException(String message) {
        super(message);
    }

    public SpreadsheetParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
