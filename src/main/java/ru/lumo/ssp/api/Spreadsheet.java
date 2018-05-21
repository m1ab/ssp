package ru.lumo.ssp.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Spreadsheet annotation
 *
 * <p>
 * Created by misha on 01.01.2017.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Spreadsheet {
    SpreadsheetFormat format();
}
