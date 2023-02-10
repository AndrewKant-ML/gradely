package it.uniroma2.dicii.ispw.gradely.beans_general;

import it.uniroma2.dicii.ispw.gradely.enums.AppelloEnum;
import it.uniroma2.dicii.ispw.gradely.enums.SessionEnum;

import java.time.LocalDate;

public class ExamBean {
    private SubjectCourseBean course;
    private AppelloEnum appello;
    private SessionEnum session;
    private LocalDate date;

    public ExamBean(SubjectCourseBean course, AppelloEnum appello, SessionEnum session, LocalDate date) {
        this.course = course;
        this.appello = appello;
        this.session = session;
        this.date = date;
    }

    public SubjectCourseBean getCourse(){
        return course;
    }

    public void setCourse(SubjectCourseBean course){
        this.course = course;
    }

    public AppelloEnum getAppello(){
        return appello;
    }

    public void setAppello(AppelloEnum appello){
        this.appello = appello;
    }

    public SessionEnum getSession(){
        return session;
    }

    public void setSession(SessionEnum session){
        this.session = session;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
