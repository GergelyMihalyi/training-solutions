package schoolrecords;

import java.util.List;

public class Tutor {
    private String name;
    private List<Subject> taughtSubjects;

    public Tutor(String name, List<Subject> taughtSubjects) {
        isNull(name);
        isNull(taughtSubjects);
        isEmpty(name);
        this.name = name;
        this.taughtSubjects = taughtSubjects;
    }

    public String getName() {
        return name;
    }

    public boolean tutorTeachingSubject(Subject subject){
        isNull(subject);
        for(Subject taughtSubject: taughtSubjects){
            if(taughtSubject.getSubjectName().equals(subject.getSubjectName())){
                return true;
            }
        }
        return false;
    }

    private void isEmpty(String str){
        if(str.trim().isEmpty()){
            throw new IllegalArgumentException();
        }
    }

    private void isNull(Object object){
        if(object == null){
            throw new NullPointerException();
        }
    }
}
