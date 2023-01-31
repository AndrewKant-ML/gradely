package it.uniroma2.dicii.ispw.gradely.facades.student;

import it.uniroma2.dicii.ispw.gradely.enums.ExamResultConfirmationEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.MissingAuthorizationException;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment.ExamEnrollment;
import it.uniroma2.dicii.ispw.gradely.session_manager.SessionManager;
import it.uniroma2.dicii.ispw.gradely.session_manager.Token;
import it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.InsertStudentsGradesControl;

public class InsertStudentsGradesStudentFacade {
    private InsertStudentsGradesControl controller;

    public InsertStudentsGradesStudentFacade(Token token) {
        try{
            SessionManager.getInstance().getSessionUserByToken(token).getRole().student();
            controller = new InsertStudentsGradesControl();
        }catch (MissingAuthorizationException e){

        }
    }
    public void acceptOrRejectExamGrade(Token token, ExamEnrollment enrollment, ExamResultConfirmationEnum decision){
        try{
            SessionManager.getInstance().getSessionUserByToken(token).getRole().student();
            controller.acceptOrRejectExamGrade(token, enrollment, decision);
        }catch (MissingAuthorizationException e){

        }
    }

}
