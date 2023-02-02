package it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades;

import it.uniroma2.dicii.ispw.gradely.beans_general.ProtocolBean;
import it.uniroma2.dicii.ispw.gradely.exceptions.MissingAuthorizationException;
import it.uniroma2.dicii.ispw.gradely.session_manager.SessionManager;
import it.uniroma2.dicii.ispw.gradely.session_manager.Token;

public class InsertStudentsGradesSecretaryFacade {
    // insert students' grades use case
    private InsertStudentsGradesControl controller;

    public InsertStudentsGradesSecretaryFacade(Token token) throws MissingAuthorizationException{
        SessionManager.getInstance().getSessionUserByTokenKey(token).getRole().castToSecretaryRole();
        controller = new InsertStudentsGradesControl();
    }

    public void confirmExamVerbaleProtocolization(Token token, ProtocolBean bean) throws MissingAuthorizationException{
        controller = new InsertStudentsGradesControl();
        controller.confirmExamVerbaleProtocolization(token,bean);
    }
}
