package it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.beans;

import it.uniroma2.dicii.ispw.gradely.general_beans.StudentBean;
import it.uniroma2.dicii.ispw.gradely.general_beans.UserBean;

public class AnagraphicalDataBean {

    private StudentBean studentBean;

    private UserBean userBean;

    public AnagraphicalDataBean(StudentBean studentBean, UserBean userBean) {
        this.studentBean = studentBean;
        this.userBean = userBean;
    }

    public StudentBean getStudentBean() {
        return studentBean;
    }

    public UserBean getUserBean() {
        return userBean;
    }
}
