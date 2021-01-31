package schoolrecords;

public class Subject {
    private String subjectName;

    public Subject(String subjectName) {
        isEmpty(subjectName);
        this.subjectName = subjectName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    private void isEmpty(String str) {
        if (str.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
    }
}
