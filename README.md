# README #



### What is this repository for? ###

* Spreadsheet Parser Core Library  
* Version 1.2 (Latest Release)
* Developer version 1.2-SNAPSHOT
* http://ssp.lu-mo.ru  


### How do I get set up? ###

> __Note!__   
> You have Java 8 SDK installed.  
> Be sure you have GitBash console in Windows or Linux bash console

```{r, engine='bash', count_lines}
mkdir -p ~/src && \
cd ~/src && \
git clone https://github.com/m1ab/ssp.git && \
cd ssp && \
git checkout ssp-1.0 && \
mvn clean install && \
cd -
```


### Usage ###

```{r, engine='java', count_lines}
    
    private static final List<Class> config = Arrays.asList(new Class[]{
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
    
    public void parse() throws Exception {
        OdsParser parser = new OdsParser();
        parser.setConfig(config); // Data types by columns
        parser.setStartRow(1); // Number of row to be skipped
        parser.setQueue(queue); // Queue which would recieve parsing messages by parsed row, to be asynchronously handled
        parser.setSheetIndex(1); // Use for multi sheet documents. Skip if one sheet available or CSV document. Default index is 0 (zero)
        List<List> data; // Result List of List of Objects
        data = parser.parse(this.getClass().getResourceAsStream("myfile.ods"));
        for (List row : data) {
            if (row !=null && !row.isEmpty()) {
                System.out.println(Arrays.toString(row.toArray()));
            }
        }
    }
    
```

### Who do I talk to? ###

* For any question feel free to contact Misha github16@lu-mo.ru  

* My linkedin profile: https://www.linkedin.com/in/mikhail-kryukov-8a2199119  
