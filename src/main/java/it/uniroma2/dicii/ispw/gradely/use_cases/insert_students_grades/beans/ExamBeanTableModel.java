package it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.beans;

import it.uniroma2.dicii.ispw.gradely.enums.AppelloEnum;
import it.uniroma2.dicii.ispw.gradely.enums.SessionEnum;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class ExamBeanTableModel {

    private SimpleStringProperty subjectCourseName;
    private SimpleStringProperty date;
    private SimpleIntegerProperty appello;
    private SimpleStringProperty session;

    public ExamBeanTableModel(String subjectCourseName, LocalDate date, AppelloEnum appello, SessionEnum session) {
        this.subjectCourseName = new SimpleStringProperty(subjectCourseName);
        this.date = new SimpleStringProperty(date.toString());
        this.appello = new SimpleIntegerProperty(appello.value);
        this.session = new SimpleStringProperty(session.toString());
    }

    public String getSubjectCourseName() {
        return subjectCourseName.get();
    }

    public void setSubjectCourseName(String subjectCourseName) {
        this.subjectCourseName.set(subjectCourseName);
    }

    public SimpleStringProperty subjectCourseNameProperty() {
        return subjectCourseName;
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public int getAppello() {
        return appello.get();
    }

    public void setAppello(int appello) {
        this.appello.set(appello);
    }

    public SimpleIntegerProperty appelloProperty() {
        return appello;
    }

    public String getSession() {
        return session.get();
    }

    public void setSession(String session) {
        this.session.set(session);
    }

    public SimpleStringProperty sessionProperty() {
        return session;
    }
}
