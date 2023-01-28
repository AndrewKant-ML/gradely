package it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course;

import it.uniroma2.dicii.ispw.gradely.general_beans.StudentBean;
import it.uniroma2.dicii.ispw.gradely.general_beans.UserBean;
import it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.beans.AnagraphicalDataBean;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class InsertAnagraphicalData {

    @FXML
    private TextField matricola, name, surname, email, codiceFiscale;

    public InsertAnagraphicalData(StudentBean studentBean, UserBean userBean) { // Constructor with student bean
        this.matricola.setText(studentBean.getMatricola());
        this.name.setText(userBean.getName());
        this.surname.setText(userBean.getSurname());
        this.email.setText(userBean.getEmail());
        this.codiceFiscale.setText(userBean.getCodiceFiscale());
    }

    public AnagraphicalDataBean getAnagraphicalData() {
        return new AnagraphicalDataBean(
                new StudentBean(this.matricola.getText()),
                new UserBean(
                        this.name.getText(),
                        this.surname.getText(),
                        this.codiceFiscale.getText(),
                        this.email.getText()
                )
        );
    }
}
