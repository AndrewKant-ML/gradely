package it.uniroma2.dicii.ispw.gradely.facades;


import it.uniroma2.dicii.ispw.gradely.exceptions.MissingAuthorizationException;
import it.uniroma2.dicii.ispw.gradely.session_manager.SessionManager;
import it.uniroma2.dicii.ispw.gradely.session_manager.Token;
import it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.InsertStudentsGradesSecretaryFacade;

public class SecretaryFacade {
    private InsertStudentsGradesSecretaryFacade insertStudentsGrades;

    public SecretaryFacade(Token token) throws MissingAuthorizationException{
        SessionManager.getInstance().getSessionUserByToken(token).getRole().getSecretaryRole();
    }

    public void InsertStudentsGrades(Token token) throws MissingAuthorizationException{
        insertStudentsGrades = new InsertStudentsGradesSecretaryFacade(token);
    }
}
