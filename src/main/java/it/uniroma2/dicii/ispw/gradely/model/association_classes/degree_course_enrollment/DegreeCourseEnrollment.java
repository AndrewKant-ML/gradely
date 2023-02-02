package it.uniroma2.dicii.ispw.gradely.model.association_classes.degree_course_enrollment;

import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourse;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;

import java.time.LocalDate;

public class DegreeCourseEnrollment {
    private LocalDate enrollmentDate;
    private Student student;
    private DegreeCourse degreeCourse;

    public DegreeCourseEnrollment(){
    }

    public DegreeCourseEnrollment(LocalDate enrollmentDate, Student student, DegreeCourse degreeCourse){
        this.enrollmentDate = enrollmentDate;
        this.student = student;
        this.degreeCourse = degreeCourse;
    }

    public LocalDate getEnrollmentDate(){
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate){
        this.enrollmentDate = enrollmentDate;
    }

    public Student getStudent(){
        return student;
    }

    public void setStudent(Student student){
        this.student = student;
    }

    public DegreeCourse getDegreeCourse(){
        return degreeCourse;
    }

    public void setDegreeCourse(DegreeCourse degreeCourse){
        this.degreeCourse = degreeCourse;
    }
}
