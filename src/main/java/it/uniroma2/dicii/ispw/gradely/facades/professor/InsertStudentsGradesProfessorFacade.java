package it.uniroma2.dicii.ispw.gradely.facades.professor;

import it.uniroma2.dicii.ispw.gradely.beans_general.ExamBean;
import it.uniroma2.dicii.ispw.gradely.beans_general.ExamEnrollmentListBean;
import it.uniroma2.dicii.ispw.gradely.beans_general.ExamListBean;
import it.uniroma2.dicii.ispw.gradely.exceptions.MissingAuthorizationException;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.Professor;
import it.uniroma2.dicii.ispw.gradely.session_manager.SessionManager;
import it.uniroma2.dicii.ispw.gradely.session_manager.Token;
import it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.InsertStudentsGradesControl;
import it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.beans.StudentGradeListBean;

public class InsertStudentsGradesProfessorFacade {
    //insert students' grades use case
    private InsertStudentsGradesControl controller;

    public InsertStudentsGradesProfessorFacade(Token token) {
        try{
            SessionManager.getInstance().getSessionUserByToken(token).getRole().professor();
            controller = new InsertStudentsGradesControl();
        }catch (MissingAuthorizationException e){

        }
    }

    public ExamListBean getGradableExams(Token token){
        try {
            return controller.getGradableExams(token);
        } catch (MissingAuthorizationException e){
            return null;
        }
    }
    public ExamEnrollmentListBean getExamEnrollments(Token token, ExamBean bean){
        return controller.getExamEnrollments(token, bean);
    }
    public void saveExamResults(Token token, StudentGradeListBean list){
        controller.saveExamResults(token, list);
    }


}
