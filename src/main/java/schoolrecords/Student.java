package schoolrecords;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Student {
    private String name;
    private List<Mark> marks = new ArrayList<>();

    public Student(String name) {
        isNull(name, "");
        isEmpty(name, "Student name must not be empty!");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public void grading(Mark mark) {
        isNull(mark, "Mark must not be null!");
        marks.add(mark);
    }

    public double calculateAverage() {
        double result = 0.0;
        if (!marks.isEmpty()) {
            for (Mark mark : marks) {
                result += mark.getMarkType().getValue();
            }
            BigDecimal bd = new BigDecimal(result / marks.size()).setScale(2, RoundingMode.HALF_UP);
            return bd.doubleValue();
        }
        return result;
    }

    public boolean hasSubject(Subject subject) {
        for (Mark mark : marks) {
            if (mark.getSubject().getSubjectName().equals(subject.getSubjectName())) {
                return true;
            }
        }
        return false;
    }

    public double calculateSubjectAverage(Subject subject) {
        isNull(subject, "");
        double result = 0.0;
        double quantity = 0.0;

        if (!marks.isEmpty()) {
            for (Mark mark : marks) {
                if (subject.getSubjectName().equals(mark.getSubject().getSubjectName())) {
                    result += mark.getMarkType().getValue();
                    quantity++;
                }
            }
            if (result == 0.0) {
                return result;
            }
            return result / quantity;
        }
        return result;
    }

    private void isEmpty(String str, String message) {
        if (str.trim().isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    private void isNull(Object object, String message) {
        if (object == null) {
            throw new NullPointerException(message);
        }
    }

    private String markToString() {
        StringBuilder markString = new StringBuilder();
        for (Mark mark : marks) {
            markString.append(mark.getSubject().getSubjectName()).append(": ").append(mark).append(", ");
        }
        markString.setLength(markString.length() - 2);
        return markString.toString();
    }

    @Override
    public String toString() {
        return name + " marks: " + markToString();
    }

    public static void main(String[] args) {

    }
}
