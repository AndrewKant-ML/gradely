package it.uniroma2.dicii.ispw.gradely.general_beans;

import it.uniroma2.dicii.ispw.gradely.enums.AppelloEnum;
import it.uniroma2.dicii.ispw.gradely.enums.SessionEnum;

public class ExamBean {
    private SubjectCourseBean course;
    private AppelloEnum appello;
    private SessionEnum sessione;

    public ExamBean(SubjectCourseBean course, AppelloEnum appello, SessionEnum sessione) {
        this.course = course;
        this.appello = appello;
        this.sessione = sessione;
    }

    public SubjectCourseBean getCourse() {
        return course;
    }

    public void setCourse(SubjectCourseBean course) {
        this.course = course;
    }

    public AppelloEnum getAppello() {
        return appello;
    }

    public void setAppello(AppelloEnum appello) {
        this.appello = appello;
    }

    public SessionEnum getSessione() {
        return sessione;
    }

    public void setSessione(SessionEnum sessione) {
        this.sessione = sessione;
    }
}
