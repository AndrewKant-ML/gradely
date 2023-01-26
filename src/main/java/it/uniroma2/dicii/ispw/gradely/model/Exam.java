package it.uniroma2.dicii.ispw.gradely.model;

import it.uniroma2.dicii.ispw.gradely.model.association_classes.ExamEnrollment;

import java.time.LocalDate;
import java.util.List;

public class Exam {
    private LocalDate enrollmentStartDate;
    private LocalDate enrollmentEndDate;
    private LocalDate examinationDate;
    private String room; //TODO: enum
    private Integer appello;
    private String sessione; //TODO: enum
    private SubjectCourse course;

    private LocalDate verbaleDate;
    private Integer verbaleNumber;
    private List<ExamEnrollment> enrollments;

    public Exam(LocalDate enrollmentStartDate, LocalDate enrollmentEndDate, LocalDate examinationDate, String room, Integer appello, String sessione, SubjectCourse course) {
        this.enrollmentStartDate = enrollmentStartDate;
        this.enrollmentEndDate = enrollmentEndDate;
        this.examinationDate = examinationDate;
        this.room = room;
        this.appello = appello;
        this.sessione = sessione;
        this.course = course;
    }

    public LocalDate getEnrollmentStartDate() {
        return enrollmentStartDate;
    }

    public void setEnrollmentStartDate(LocalDate enrollmentStartDate) {
        this.enrollmentStartDate = enrollmentStartDate;
    }

    public LocalDate getEnrollmentEndDate() {
        return enrollmentEndDate;
    }

    public void setEnrollmentEndDate(LocalDate enrollmentEndDate) {
        this.enrollmentEndDate = enrollmentEndDate;
    }

    public LocalDate getExaminationDate() {
        return examinationDate;
    }

    public void setExaminationDate(LocalDate examinationDate) {
        this.examinationDate = examinationDate;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Integer getAppello() {
        return appello;
    }

    public void setAppello(Integer appello) {
        this.appello = appello;
    }

    public String getSessione() {
        return sessione;
    }

    public void setSessione(String sessione) {
        this.sessione = sessione;
    }

    public SubjectCourse getCourse() {
        return course;
    }

    public void setCourse(SubjectCourse course) {
        this.course = course;
    }

    public LocalDate getVerbaleDate() {
        return verbaleDate;
    }

    public void setVerbaleDate(LocalDate verbaleDate) {
        this.verbaleDate = verbaleDate;
    }

    public Integer getVerbaleNumber() {
        return verbaleNumber;
    }

    public void setVerbaleNumber(Integer verbaleNumber) {
        this.verbaleNumber = verbaleNumber;
    }

    public List<ExamEnrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<ExamEnrollment> enrollments) {
        this.enrollments = enrollments;
    }
}
