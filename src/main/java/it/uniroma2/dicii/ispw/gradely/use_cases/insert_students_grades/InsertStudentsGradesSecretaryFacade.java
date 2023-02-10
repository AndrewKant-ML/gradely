package it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades;

import it.uniroma2.dicii.ispw.gradely.beans_general.ProtocolBean;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.session_manager.SessionManager;

public class InsertStudentsGradesSecretaryFacade {
    // insert students' grades use case
    private InsertStudentsGradesControl controller;

    public InsertStudentsGradesSecretaryFacade(String tokenKey) throws MissingAuthorizationException{
        SessionManager.getInstance().getSessionUserByTokenKey(tokenKey).getRole().getSecretaryRole();
        controller = new InsertStudentsGradesControl();
    }

    public void confirmExamVerbaleProtocolization(String tokenKey, ProtocolBean bean) throws MissingAuthorizationException, DAOException, PropertyException, ResourceNotFoundException, ObjectNotFoundException {
        controller.confirmExamVerbaleProtocolization(tokenKey,bean);
    }
}
