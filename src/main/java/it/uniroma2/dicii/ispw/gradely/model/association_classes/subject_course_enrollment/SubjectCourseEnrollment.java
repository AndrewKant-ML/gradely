package it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_enrollment;

import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;

import java.time.LocalDate;

public class SubjectCourseEnrollment {
    private SubjectCourse subjectCourse;
    private Student student;
    private LocalDate date;

    public SubjectCourseEnrollment(){
    }

    public SubjectCourseEnrollment(SubjectCourse subjectCourse, Student student, LocalDate date){
        this.subjectCourse = subjectCourse;
        this.student = student;
        this.date = date;
    }

    public SubjectCourse getSubjectCourse(){
        return subjectCourse;
    }

    public void setSubjectCourse(SubjectCourse subjectCourse){
        this.subjectCourse = subjectCourse;
    }

    public Student getStudent(){
        return student;
    }

    public void setStudent(Student student){
        this.student = student;
    }

    public LocalDate getDate(){
        return date;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }
}
