package it.uniroma2.dicii.ispw.gradely.facades;

import it.uniroma2.dicii.ispw.gradely.beans_general.ExamBean;
import it.uniroma2.dicii.ispw.gradely.beans_general.ExamEnrollmentListBean;
import it.uniroma2.dicii.ispw.gradely.beans_general.ExamListBean;
import it.uniroma2.dicii.ispw.gradely.exceptions.MissingAuthorizationException;
import it.uniroma2.dicii.ispw.gradely.session_manager.Token;
import it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.InsertStudentsGradesControl;
import it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.beans.StudentGradeListBean;

public class ProfessorFacade {
    //insert students' grades use case
    private InsertStudentsGradesControl controller;

    public ExamListBean getGradableExams(Token token){
        controller = new InsertStudentsGradesControl();
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
