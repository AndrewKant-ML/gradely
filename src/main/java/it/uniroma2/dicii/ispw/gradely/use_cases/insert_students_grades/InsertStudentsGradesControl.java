package it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades;

import it.uniroma2.dicii.ispw.gradely.general_beans.ExamListBean;
import it.uniroma2.dicii.ispw.gradely.lazy_factories.ExamLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.Exam;
import it.uniroma2.dicii.ispw.gradely.session_manager.SessionManager;
import it.uniroma2.dicii.ispw.gradely.session_manager.Token;

import java.util.List;

public class InsertStudentsGradesControl {
    public ExamListBean getGradableExams(Token token){ //TODO exceptions
        List<Exam> list = ExamLazyFactory.getInstance().getGradableExams(SessionManager.getInstance().getLazySessionUser(token).getRole().Professor());
        return new ExamListBean();
    }
}
