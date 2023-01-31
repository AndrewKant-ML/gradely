package it.uniroma2.dicii.ispw.gradely.facades.secretary;

import it.uniroma2.dicii.ispw.gradely.beans_general.ProtocolBean;
import it.uniroma2.dicii.ispw.gradely.exceptions.MissingAuthorizationException;
import it.uniroma2.dicii.ispw.gradely.session_manager.SessionManager;
import it.uniroma2.dicii.ispw.gradely.session_manager.Token;
import it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.InsertStudentsGradesControl;

public class InsertStudentsGradesSecretaryFacade {
    // insert students' grades use case
    private InsertStudentsGradesControl controller;

    public InsertStudentsGradesSecretaryFacade(Token token) {
        try{
            SessionManager.getInstance().getSessionUserByToken(token).getRole().secretary();
            controller = new InsertStudentsGradesControl();
        }catch (MissingAuthorizationException e){

        }
    }

    public void confirmExamVerbaleProtocolization(Token token, ProtocolBean bean){
        controller = new InsertStudentsGradesControl();
        try {
            controller.confirmExamVerbaleProtocolization(token,bean);
        } catch (RuntimeException e){

        }
    }
}
