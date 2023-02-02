package it.uniroma2.dicii.ispw.gradely.model.role.student;

import it.uniroma2.dicii.ispw.gradely.model.association_classes.degree_course_enrollment.DegreeCourseEnrollment;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment.ExamEnrollment;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_enrollment.SubjectCourseEnrollment;
import it.uniroma2.dicii.ispw.gradely.model.role.AbstractRole;
import it.uniroma2.dicii.ispw.gradely.model.title.Title;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.time.LocalDate;
import java.util.List;

public class Student extends AbstractRole {
    private String matricola;
    private LocalDate registrationDate;
    private List<Title> titles;
    private List<DegreeCourseEnrollment> degreeCourseEnrollments;
    private List<SubjectCourseEnrollment> subjectCourseEnrollments;
    private List<ExamEnrollment> examEnrollments;

    protected Student(User user, String matricola, LocalDate registrationDate, List<Title> titoli) {
        super(user);
        this.matricola = matricola;
        this.registrationDate = registrationDate;
        this.titles = titoli;
    }

    @Override
    public Student castToStudentRole() {
        return this;
    }

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    public List<DegreeCourseEnrollment> getDegreeCourseEnrollments() {
        return degreeCourseEnrollments;
    }

    public void setDegreeCourseEnrollments(List<DegreeCourseEnrollment> degreeCourseEnrollments) {
        this.degreeCourseEnrollments = degreeCourseEnrollments;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<Title> getTitles() {
        return titles;
    }

    public void setTitles(List<Title> titles) {
        this.titles = titles;
    }

    public List<SubjectCourseEnrollment> getSubjectCourseEnrollments() {
        return subjectCourseEnrollments;
    }

    public void setSubjectCourseEnrollments(List<SubjectCourseEnrollment> subjectCourseEnrollments) {
        this.subjectCourseEnrollments = subjectCourseEnrollments;
    }

    public List<ExamEnrollment> getExamEnrollments() {
        return examEnrollments;
    }

    public void setExamEnrollments(List<ExamEnrollment> examEnrollments) {
        this.examEnrollments = examEnrollments;
    }
}
