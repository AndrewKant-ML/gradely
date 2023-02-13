package it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades;

import it.uniroma2.dicii.ispw.gradely.beans_general.ExamEnrollmentBean;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.session_manager.SessionManager;
import it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.beans.ExamResultConfirmationBean;

public class InsertStudentsGradesStudentFacade {
    private InsertStudentsGradesControl controller;

    public InsertStudentsGradesStudentFacade(String tokenKey) throws MissingAuthorizationException{
        SessionManager.getInstance().getSessionUserByTokenKey(tokenKey).getRole().getStudentRole();
        controller = new InsertStudentsGradesControl();
    }
    public void acceptOrRejectExamGrade(String tokenKey, ExamEnrollmentBean enrollment, ExamResultConfirmationBean decision) throws MissingAuthorizationException, DAOException, UserNotFoundException, PropertyException, WrongListQueryIdentifierValue, ObjectNotFoundException, ResourceNotFoundException, UnrecognizedRoleException, WrongDegreeCourseCodeException {
        SessionManager.getInstance().getSessionUserByTokenKey(tokenKey).getRole().getStudentRole();
        controller.acceptOrRejectExamGrade(tokenKey, enrollment, decision);

    }

}
