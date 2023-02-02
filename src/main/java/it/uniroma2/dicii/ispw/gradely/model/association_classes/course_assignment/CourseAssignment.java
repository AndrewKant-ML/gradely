package it.uniroma2.dicii.ispw.gradely.model.association_classes.course_assignment;

import it.uniroma2.dicii.ispw.gradely.model.role.professor.Professor;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;

public class CourseAssignment {
    private SubjectCourse subjectCourse;
    private Professor professor;


    public CourseAssignment(){
    }
    public CourseAssignment(SubjectCourse subjectCourse, Professor professor){
        this.subjectCourse = subjectCourse;
        this.professor = professor;
    }

    public SubjectCourse getSubjectCourse(){
        return subjectCourse;
    }

    public void setSubjectCourse(SubjectCourse subjectCourse){
        this.subjectCourse = subjectCourse;
    }

    public Professor getProfessor(){
        return professor;
    }

    public void setProfessor(Professor professor){
        this.professor = professor;
    }
}
