package schoolrecords;

public class Mark {
    public static final String ERROR_MESSAGE = "Both subject and tutor must be provided!";
    private MarkType markType;
    private Subject subject;
    private Tutor tutor;


    public Mark(MarkType markType, Subject subject, Tutor tutor) {
        isNull(markType,ERROR_MESSAGE);
        isNull(subject,ERROR_MESSAGE);
        isNull(tutor,ERROR_MESSAGE);
        this.markType = markType;
        this.subject = subject;
        this.tutor = tutor;
    }

    public Mark(String markType, Subject subject, Tutor tutor) {
        isNull(markType, ERROR_MESSAGE);
        isNull(subject,ERROR_MESSAGE);
        isNull(tutor,ERROR_MESSAGE);
        isEmpty(markType,ERROR_MESSAGE);
        this.markType = MarkType.valueOf(markType);
        this.subject = subject;
        this.tutor = tutor;
    }

    public MarkType getMarkType() {
        return markType;
    }

    public Subject getSubject() {
        return subject;
    }

    public Tutor getTutor() {
        return tutor;
    }

    private void isEmpty(String str,String message){
        if(str.trim().isEmpty()){
            throw new IllegalArgumentException(message);
        }
    }

    private void isNull(Object object,String message){
        if(object == null){
            throw new NullPointerException(message);
        }
    }

    @Override
    public String toString() {
        return "" + markType;
    }
}
