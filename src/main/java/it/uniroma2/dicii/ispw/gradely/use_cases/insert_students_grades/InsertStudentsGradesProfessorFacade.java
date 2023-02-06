package it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades;

import it.uniroma2.dicii.ispw.gradely.beans_general.ExamBean;
import it.uniroma2.dicii.ispw.gradely.beans_general.ExamEnrollmentListBean;
import it.uniroma2.dicii.ispw.gradely.beans_general.ExamListBean;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.MissingAuthorizationException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;
import it.uniroma2.dicii.ispw.gradely.session_manager.SessionManager;
import it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.beans.StudentGradeListBean;

public class InsertStudentsGradesProfessorFacade {
    //insert students' grades use case
    private InsertStudentsGradesControl controller;

    public InsertStudentsGradesProfessorFacade(String tokenKey) throws MissingAuthorizationException{
        SessionManager.getInstance().getSessionUserByTokenKey(tokenKey).getRole().castToProfessorRole();
        controller = new InsertStudentsGradesControl();
    }

    public ExamListBean getGradableExams(String tokenKey) throws MissingAuthorizationException, DAOException {
        return controller.getGradableExams(tokenKey);
    }
    public ExamEnrollmentListBean getExamEnrollments(String tokenKey, ExamBean bean) throws MissingAuthorizationException, DAOException, PropertyException, ResourceNotFoundException {
        return controller.getExamEnrollments(tokenKey, bean);
    }
    public void saveExamResults(String tokenKey, StudentGradeListBean list) throws MissingAuthorizationException, DAOException{
        controller.saveExamResults(tokenKey, list);
    }


}
