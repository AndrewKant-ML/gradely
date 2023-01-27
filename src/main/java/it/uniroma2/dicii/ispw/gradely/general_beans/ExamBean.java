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


}
