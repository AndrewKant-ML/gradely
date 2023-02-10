package it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.beans;

import it.uniroma2.dicii.ispw.gradely.enums.AppelloEnum;
import it.uniroma2.dicii.ispw.gradely.enums.SessionEnum;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class ExamBeanTableModel {

    private final SimpleStringProperty subjectCourseNameProperty;
    private final SimpleStringProperty dateProperty;
    private final SimpleIntegerProperty appelloProperty;
    private final SimpleStringProperty sessionProperty;

    public ExamBeanTableModel(String subjectCourseNameProperty, LocalDate dateProperty, AppelloEnum appelloProperty, SessionEnum sessionProperty) {
        this.subjectCourseNameProperty = new SimpleStringProperty(subjectCourseNameProperty);
        this.dateProperty = new SimpleStringProperty(dateProperty.toString());
        this.appelloProperty = new SimpleIntegerProperty(appelloProperty.value);
        this.sessionProperty = new SimpleStringProperty(sessionProperty.toString());
    }

    public String getSubjectCourseNameProperty() {
        return subjectCourseNameProperty.get();
    }

    public String getDateProperty() {
        return dateProperty.get();
    }

    public void setDateProperty(String dateProperty) {
        this.dateProperty.set(dateProperty);
    }

    public SimpleStringProperty datePropertyProperty() {
        return dateProperty;
    }

    public int getAppelloProperty() {
        return appelloProperty.get();
    }

    public void setAppelloProperty(int appelloProperty) {
        this.appelloProperty.set(appelloProperty);
    }

    public SimpleIntegerProperty appelloPropertyProperty() {
        return appelloProperty;
    }

    public String getSessionProperty() {
        return sessionProperty.get();
    }

    public void setSessionProperty(String sessionProperty) {
        this.sessionProperty.set(sessionProperty);
    }

    public SimpleStringProperty sessionPropertyProperty() {
        return sessionProperty;
    }
}
