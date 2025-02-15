package com.example.Hari.NewSpringBatch.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class CustomLineMapper extends DefaultLineMapper<DataRecord> {
    private static final Logger log = LoggerFactory.getLogger(CustomLineMapper.class);

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private static final DateTimeFormatter dateFormatterSub = DateTimeFormatter.ofPattern("d/M/yyyy");
    final ArrayList<String> fields;
    final String delimiter;

    public CustomLineMapper(ArrayList<String> fields, String delimiter) {
        this.fields = fields;
        this.delimiter = delimiter;
    }


    @Override
    public DataRecord mapLine(String line, int lineNumber) throws Exception {

        log.info("Line Number in csvFile : {}",lineNumber-1);
        log.info("Data present in line number {} is {}",lineNumber-1,line);

        DataRecord record = new DataRecord();
        record.setLineNumber(lineNumber);
        record.setDataMap(lineToMap(fields, line, delimiter));
        return record;
    }



    public static HashMap<String, String> lineToMap(ArrayList<String> fields, String line, String delimiter) {
        HashMap<String, String> dataMap = new HashMap<>();
        String[] lineArr = line.split(Pattern.quote(delimiter) + "(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);

        log.info("LINE ARR :{}",lineArr.length);
        log.info("LINE ARR :{}",lineArr);

        for (int i = 0; i < fields.size(); i++) {
            String field = fields.get(i);
            log.info("field :{}",field);

            if (i < lineArr.length) {
                if (lineArr[i].isEmpty() || lineArr[i] == null)
                    dataMap.put(field, null);
                else {
                    dataMap.put(field, lineArr[i]);
                }
            } else {
                dataMap.put(field, null);            }
        }
        log.info("dataMap {}",dataMap);
        return dataMap;



//        for (String field : fields) {
//            int headerIndex = headers.indexOf(field); // Find the position of the field in headers
//            if (headerIndex != -1 && headerIndex < lineArr.length) {
//                String value = lineArr[headerIndex];
//                dataMap.put(field, value.isEmpty() ? null : value);
//            } else {
//                // Field is missing or out of bounds
//                dataMap.put(field, null);
//            }
//        }



        }




/*    public static boolean isDateTime(String dateInString) {
        try {
            dateTimeFormatter.parse(dateInString.trim());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean isDateOnly(String dateInString) {
        try {
            dateFormatter.parse(dateInString);
        } catch (Exception e) {
            try {
                dateFormatterSub.parse(dateInString);
            } catch (Exception ex) {
               // log.info("field : {}", dateInString);
                return false;
            }
        }
        return true;
    }*/

}
