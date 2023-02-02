package it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades;

import it.uniroma2.dicii.ispw.gradely.enums.ExamResultConfirmationEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.MissingAuthorizationException;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment.ExamEnrollment;
import it.uniroma2.dicii.ispw.gradely.session_manager.SessionManager;
import it.uniroma2.dicii.ispw.gradely.session_manager.Token;

public class InsertStudentsGradesStudentFacade {
    private InsertStudentsGradesControl controller;

    public InsertStudentsGradesStudentFacade(Token token) throws MissingAuthorizationException{
        SessionManager.getInstance().getSessionUserByToken(token).getRole().getStudentRole();
        controller = new InsertStudentsGradesControl();
    }
    public void acceptOrRejectExamGrade(Token token, ExamEnrollment enrollment, ExamResultConfirmationEnum decision)throws MissingAuthorizationException{
        SessionManager.getInstance().getSessionUserByToken(token).getRole().getStudentRole();
        controller.acceptOrRejectExamGrade(token, enrollment, decision);

    }

}
