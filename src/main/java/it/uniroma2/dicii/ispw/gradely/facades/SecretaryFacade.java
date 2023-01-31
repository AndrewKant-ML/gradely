package it.uniroma2.dicii.ispw.gradely.facades;

import it.uniroma2.dicii.ispw.gradely.beans_general.ProtocolBean;
import it.uniroma2.dicii.ispw.gradely.session_manager.Token;
import it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.InsertStudentsGradesControl;

public class SecretaryFacade {
    // insert students' grades use case
    private InsertStudentsGradesControl controller;
    public void confirmExamVerbaleProtocolization(Token token, ProtocolBean bean){
        controller = new InsertStudentsGradesControl();
        try {
            controller.confirmExamVerbaleProtocolization(token,bean);
        } catch (RuntimeException e){

        }
    }
}
