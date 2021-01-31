package schoolrecords;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClassRecords {
    private String className;
    private Random rnd;
    private List<Student> students = new ArrayList<>();

    public ClassRecords(String className, Random rnd) {
        isNull(className);
        isNull(rnd);
        isEmpty(className, "Class name must not be empty!");
        this.className = className;
        this.rnd = rnd;
    }

    public String getClassName() {
        return className;
    }

    public boolean addStudent(Student student) {
        isNull(student);
        Student addStudent = findStudent(student.getName());
        if (addStudent == null) {
            students.add(student);
            return true;
        }
        return false;
    }

    public boolean removeStudent(Student student) {
        isNull(student);
        Student deletedStudent = findStudent(student.getName());
        if (deletedStudent != null) {
            students.remove(deletedStudent);
            return true;
        }
        return false;
    }

    public double calculateClassAverage() {
        if (students.isEmpty()) {
            throw new ArithmeticException("No student in the class, average calculation aborted!");
        }
        studentCheck("No student in the class, average calculation aborted!");
        double result = 0.0;
        for (Student student : students) {
            averageCalculationConditions(student, "No marks present, average calculation aborted!");
            result += student.calculateAverage();
        }
        BigDecimal bd = new BigDecimal(result / students.size()).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public double calculateClassAverageBySubject(Subject subject) {
        studentCheck("No student in the class, average calculation aborted!");
        isNull(subject);
        double result = 0.0;
        double quantity = 0.0;
        for (Student student : students) {
            averageCalculationConditions(student, "");
            if (student.hasSubject(subject)) {
                result += student.calculateSubjectAverage(subject);
                quantity++;
            }
        }
        return result / quantity;
    }

    public Student findStudent(String name) {
        isEmpty(name, "Student name must not be empty!");

        for (Student student : students) {
            if (name.equals(student.getName())) {
                return student;
            }
        }
        return null;
    }

    public Student findStudentByName(String name) {
        isEmpty(name, "Student name must not be empty!");
        studentCheck("No students to search!");
        for (Student student : students) {
            if (name.equals(student.getName())) {
                return student;
            }
        }
        throw new IllegalArgumentException("Student by this name cannot be found! " + name);
    }

    public Student repetition() {
        studentCheck("No students to select for repetition!");
        return students.get(rnd.nextInt(students.size()));
    }

    public List<StudyResultByName> listStudyResults() {
        studentCheck("No students to search!");
        List<StudyResultByName> studyResultByNames = new ArrayList<>();
        for (Student student : students) {
            studyResultByNames.add(new StudyResultByName(student.getName(), student.calculateAverage()));
        }
        return studyResultByNames;
    }

    public String listStudentNames() {
        studentCheck("No students to search!");
        StringBuilder names = new StringBuilder();

        for (Student student : students) {
            names.append(student.getName()).append(", ");
        }
        names.setLength(names.length() - 2);
        return names.toString();
    }

    private void averageCalculationConditions(Student student, String message) {
        if (student.getMarks().isEmpty()) {
            throw new ArithmeticException(message);
        }
    }

    private void studentCheck(String message) {
        if (students.isEmpty()) {
            throw new IllegalStateException(message);
        }
    }

    private void isNull(Object object) {
        if (object == null) {
            throw new NullPointerException("Null");
        }
    }

    private void isEmpty(String str, String message) {
        if (str.trim().isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void main(String[] args) {
    }
}
