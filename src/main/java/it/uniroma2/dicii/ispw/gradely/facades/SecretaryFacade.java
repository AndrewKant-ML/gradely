package it.uniroma2.dicii.ispw.gradely.facades;


import it.uniroma2.dicii.ispw.gradely.exceptions.MissingAuthorizationException;
import it.uniroma2.dicii.ispw.gradely.session_manager.SessionManager;
import it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.InsertStudentsGradesSecretaryFacade;

public class SecretaryFacade {
    private InsertStudentsGradesSecretaryFacade insertStudentsGrades;

    public SecretaryFacade(String tokenKey) throws MissingAuthorizationException{
        SessionManager.getInstance().getSessionUserByTokenKey(tokenKey).getRole().getSecretaryRole();
    }

    public void insertStudentsGrades(String tokenKey) throws MissingAuthorizationException{
        insertStudentsGrades = new InsertStudentsGradesSecretaryFacade(tokenKey);
    }
}
