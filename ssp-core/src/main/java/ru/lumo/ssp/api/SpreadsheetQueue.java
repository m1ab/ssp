package ru.lumo.ssp.api;

import java.util.Queue;

/**
 * Queue for parsing process feedback
 *
 * <p>
 * Created by misha on 01.01.2017.
 */
public interface SpreadsheetQueue {
    Queue<String> getQueue();
}
