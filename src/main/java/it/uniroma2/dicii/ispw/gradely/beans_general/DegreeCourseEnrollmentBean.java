package it.uniroma2.dicii.ispw.gradely.beans_general;

import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourse;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;

import java.time.LocalDate;

public class DegreeCourseEnrollmentBean {
    private LocalDate enrollmentDate;
    private StudentBean student;
    private DegreeCourseBean degreeCourse;

    public DegreeCourseEnrollmentBean(LocalDate enrollmentDate, StudentBean student, DegreeCourseBean degreeCourse) {
        this.enrollmentDate = enrollmentDate;
        this.student = student;
        this.degreeCourse = degreeCourse;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public StudentBean getStudent() {
        return student;
    }

    public void setStudent(StudentBean student) {
        this.student = student;
    }

    public DegreeCourseBean getDegreeCourse() {
        return degreeCourse;
    }

    public void setDegreeCourse(DegreeCourseBean degreeCourse) {
        this.degreeCourse = degreeCourse;
    }
}
