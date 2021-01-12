package exceptions;

public enum ProcessingResult {
    NO_ERROR(0), COMMENT(1), WORD_COUNT_ERROR(2), VALUE_ERROR(4), DATE_ERROR(8), VALUE_AND_DATE_ERROR(12);

    private int errorCode;

    ProcessingResult(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
