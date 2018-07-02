package ru.lumo.ssp.test;

import org.junit.Assert;
import ru.lumo.ssp.api.SpreadsheetFormat;
import ru.lumo.ssp.api.SpreadsheetParser;
import ru.lumo.ssp.api.SpreadsheetQueue;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


public abstract class AbstractSpreadsheetTest {

    public static final List<Class> DEFAULT_CONFIG = Arrays.asList(new Class[]{
            String.class,   //  1   Наименование
            Double.class,   //  2   Параметр
            Boolean.class,  //  3   Флаг
            Date.class      //  4   Дата
    });

    private SpreadsheetQueue queue = new SpreadsheetQueue() {
        private ConcurrentLinkedQueue<String> messages = new ConcurrentLinkedQueue<>();

        @Override
        public Queue<String> getQueue() {
            return messages;
        }
    };

    protected abstract SpreadsheetFormat getFormat();

    protected abstract SpreadsheetParser initParser();

    protected List<Class> getConfig() {
        return DEFAULT_CONFIG;
    }

    protected String getResource() {
        return "spreadsheet";
    }

    protected InputStream getInputStream() {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(getResource() + "." + getFormat());
        Assert.assertNotNull(resourceAsStream);
        return resourceAsStream;
    }

    protected void parse() throws Exception {
        List<List> data = getParser().parse(getInputStream());
        Assert.assertTrue(data.size() == 3);
        for (List row : data) {
            int size = row.size();
            Assert.assertTrue(size == 4);
            System.out.println(Arrays.toString(row.toArray()));
            for (Object o : row) {
                Assert.assertNotNull(o);
            }
        }
        Queue<String> messages = this.queue.getQueue();
        int counter = 0;
        while (true) {
            String poll = messages.poll();
            if (poll == null) break;
//            System.out.println(poll);
            counter++;
        }
        Assert.assertTrue(counter / 2 == data.size() || counter == data.size());
    }

    protected SpreadsheetParser getParser() {
        SpreadsheetParser parser = initParser();
        parser.setConfig(getConfig());
        parser.setStartRow(0);
        parser.setQueue(queue);
        return parser;
    }

}
