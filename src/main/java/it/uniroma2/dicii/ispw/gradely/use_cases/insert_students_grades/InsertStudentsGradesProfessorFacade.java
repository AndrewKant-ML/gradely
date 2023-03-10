package it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades;

import it.uniroma2.dicii.ispw.gradely.beans_general.ExamBean;
import it.uniroma2.dicii.ispw.gradely.beans_general.ExamEnrollmentListBean;
import it.uniroma2.dicii.ispw.gradely.beans_general.ExamListBean;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.session_manager.SessionManager;
import it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.beans.StudentGradeListBean;

public class InsertStudentsGradesProfessorFacade {
    //insert students' grades use case
    private final InsertStudentsGradesControl controller;

    public InsertStudentsGradesProfessorFacade(String tokenKey) throws MissingAuthorizationException{
        SessionManager.getInstance().getSessionUserByTokenKey(tokenKey).getRole().getProfessorRole();
        controller = new InsertStudentsGradesControl();
    }

    public ExamListBean getGradableExams(String tokenKey) throws MissingAuthorizationException, DAOException, UserNotFoundException, UnrecognizedRoleException, WrongDegreeCourseCodeException, WrongListQueryIdentifierValue {
        return controller.getGradableExams(tokenKey);
    }

    public ExamEnrollmentListBean getExamEnrollments(String tokenKey, ExamBean bean) throws MissingAuthorizationException, DAOException, PropertyException, ResourceNotFoundException, UserNotFoundException, UnrecognizedRoleException, ObjectNotFoundException, WrongDegreeCourseCodeException, WrongListQueryIdentifierValue {
        return controller.getExamEnrollments(tokenKey, bean);
    }

    public void saveExamResults(String tokenKey, StudentGradeListBean list) throws MissingAuthorizationException, DAOException, UserNotFoundException, PropertyException, ObjectNotFoundException, ResourceNotFoundException, UnrecognizedRoleException, WrongDegreeCourseCodeException, WrongListQueryIdentifierValue {
        controller.saveExamResults(tokenKey, list);
    }


}
