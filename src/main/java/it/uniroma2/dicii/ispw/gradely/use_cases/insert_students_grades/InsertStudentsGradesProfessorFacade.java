package it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades;

import it.uniroma2.dicii.ispw.gradely.beans_general.ExamBean;
import it.uniroma2.dicii.ispw.gradely.beans_general.ExamEnrollmentListBean;
import it.uniroma2.dicii.ispw.gradely.beans_general.ExamListBean;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.MissingAuthorizationException;
import it.uniroma2.dicii.ispw.gradely.session_manager.SessionManager;
import it.uniroma2.dicii.ispw.gradely.session_manager.Token;
import it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.beans.StudentGradeListBean;

public class InsertStudentsGradesProfessorFacade {
    //insert students' grades use case
    private InsertStudentsGradesControl controller;

    public InsertStudentsGradesProfessorFacade(Token token) throws MissingAuthorizationException{
        SessionManager.getInstance().getSessionUserByToken(token).getRole().getProfessorRole();
        controller = new InsertStudentsGradesControl();
    }

    public ExamListBean getGradableExams(Token token) throws MissingAuthorizationException, DAOException {
        return controller.getGradableExams(token);
    }
    public ExamEnrollmentListBean getExamEnrollments(Token token, ExamBean bean) throws MissingAuthorizationException, DAOException{
        return controller.getExamEnrollments(token, bean);
    }
    public void saveExamResults(Token token, StudentGradeListBean list) throws MissingAuthorizationException, DAOException{
        controller.saveExamResults(token, list);
    }


}
