package exceptions;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class FaultList {

    public static final String SEPARATOR = ",";
    public static final int SERIAL_NUMBER = 0;
    public static final int MEASUREMENT_VALUE = 1;
    public static final int MEASUREMENT_DATE = 2;

    public List<String> processLines(List<String> lines) {
        if (lines == null) {
            throw new IllegalArgumentException("lines is null");
        }
        List<String> errors = new ArrayList<>();

        for (String line : lines) {
            String result = processLine(line);
            if (result != null) {
                errors.add(result);
            }
        }

        return errors;
    }

    private String processLine(String line) {
        ProcessingResult processResult = ProcessingResult.NO_ERROR;
        String[] parts = line.split(SEPARATOR);
        if (commentCheck(parts[SERIAL_NUMBER])) {
            return null;
        }
        if (wordCountError(parts)) {
            return parts[SERIAL_NUMBER] + "," + ProcessingResult.WORD_COUNT_ERROR.getErrorCode();
        }

        boolean valueError = valueError(parts[MEASUREMENT_VALUE]);
        boolean dateError = dateError(parts[MEASUREMENT_DATE]);

        if (valueError && dateError) {
            processResult = ProcessingResult.VALUE_AND_DATE_ERROR;
        } else if (valueError) {
            processResult = ProcessingResult.VALUE_ERROR;
        } else if (dateError) {
            processResult = ProcessingResult.DATE_ERROR;
        }
        if (processResult.getErrorCode() > 1) {
            return parts[SERIAL_NUMBER] + "," + processResult.getErrorCode();
        } else {
            return null;
        }

    }

    private boolean wordCountError(String[] words) {
        return words.length != 3;
    }

    private boolean commentCheck(String word) {
        try {
            Integer.parseInt(word);
        } catch (NumberFormatException e) {
            return true;
        }
        return false;
    }

    private boolean valueError(String word) {
        try {
            Double.parseDouble(word);
        } catch (NumberFormatException e) {
            return true;
        }
        return false;
    }

    private boolean dateError(String word) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy.MM.dd.");
            df.parse(word);
        } catch (ParseException e) {
            return true;
        }
        return false;
    }

}
