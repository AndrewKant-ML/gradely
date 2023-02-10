package it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.beans;

import it.uniroma2.dicii.ispw.gradely.enums.ResultEnum;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ChoiceBox;

public class ExamResultBeanTableModel {

    private final SimpleStringProperty studentNameProperty;
    private final SimpleStringProperty studentMatricolaProperty;
    private final SimpleObjectProperty<ChoiceBox<ResultEnum>> resultProperty;
    private final SimpleIntegerProperty gradeProperty;

    public ExamResultBeanTableModel(String studentNameProperty, String studentMatricolaProperty, ChoiceBox<ResultEnum> resultProperty) {
        this.studentNameProperty = new SimpleStringProperty(studentNameProperty);
        this.studentMatricolaProperty = new SimpleStringProperty(studentMatricolaProperty);
        this.resultProperty = new SimpleObjectProperty<>(resultProperty);
        this.gradeProperty = new SimpleIntegerProperty(0);
    }

    public String getStudentNameProperty() {
        return this.studentNameProperty.get();
    }

    public String getStudentMatricolaProperty() {
        return this.studentMatricolaProperty.get();
    }

    public ChoiceBox<ResultEnum> getResultProperty() {
        return this.resultProperty.get();
    }

    public Integer getGradeProperty() {
        return this.gradeProperty.get();
    }
}
