package it.uniroma2.dicii.ispw.gradely.model.association_classes;

import it.uniroma2.dicii.ispw.gradely.model.Professor;
import it.uniroma2.dicii.ispw.gradely.model.SubjectCourse;

public class CourseAssignment {
    private SubjectCourse subjectCourse;
    private Professor professor;

    public CourseAssignment(SubjectCourse subjectCourse, Professor professor) {
        this.subjectCourse = subjectCourse;
        this.professor = professor;
    }

    public SubjectCourse getSubjectCourse() {
        return subjectCourse;
    }

    public void setSubjectCourse(SubjectCourse subjectCourse) {
        this.subjectCourse = subjectCourse;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
