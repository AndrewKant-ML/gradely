package it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades;

import it.uniroma2.dicii.ispw.gradely.enums.ExamResultConfirmationEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.MissingAuthorizationException;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment.ExamEnrollment;
import it.uniroma2.dicii.ispw.gradely.session_manager.SessionManager;

public class InsertStudentsGradesStudentFacade {
    private InsertStudentsGradesControl controller;

    public InsertStudentsGradesStudentFacade(String tokenKey) throws MissingAuthorizationException{
        SessionManager.getInstance().getSessionUserByTokenKey(tokenKey).getRole().castToStudentRole();
        controller = new InsertStudentsGradesControl();
    }
    public void acceptOrRejectExamGrade(String tokenKey, ExamEnrollment enrollment, ExamResultConfirmationEnum decision)throws MissingAuthorizationException{
        SessionManager.getInstance().getSessionUserByTokenKey(tokenKey).getRole().castToStudentRole();
        controller.acceptOrRejectExamGrade(tokenKey, enrollment, decision);

    }

}