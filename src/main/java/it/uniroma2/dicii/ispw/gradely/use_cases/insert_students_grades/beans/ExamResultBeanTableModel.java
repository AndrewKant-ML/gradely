package it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.beans;

import it.uniroma2.dicii.ispw.gradely.enums.ResultEnum;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ChoiceBox;

public class ExamResultBeanTableModel {

    private final SimpleStringProperty studentName;
    private final SimpleStringProperty studentMatricola;
    private final SimpleObjectProperty<ChoiceBox<ResultEnum>> result;
    private final SimpleIntegerProperty grade;

    public ExamResultBeanTableModel(String studentName, String studentMatricola, ChoiceBox<ResultEnum> result) {
        this.studentName = new SimpleStringProperty(studentName);
        this.studentMatricola = new SimpleStringProperty(studentMatricola);
        this.result = new SimpleObjectProperty<>(result);
        this.grade = new SimpleIntegerProperty(0);
    }

    public String getStudentName() {
        return this.studentName.get();
    }

    public String getStudentMatricola() {
        return this.studentMatricola.get();
    }

    public ChoiceBox<ResultEnum> getResult() {
        return this.result.get();
    }

    public Integer getGrade() {
        return this.grade.get();
    }
}
