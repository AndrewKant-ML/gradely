package it.uniroma2.dicii.ispw.gradely.facades.secretary;


import it.uniroma2.dicii.ispw.gradely.exceptions.MissingAuthorizationException;
import it.uniroma2.dicii.ispw.gradely.facades.professor.InsertStudentsGradesProfessorFacade;
import it.uniroma2.dicii.ispw.gradely.session_manager.SessionManager;
import it.uniroma2.dicii.ispw.gradely.session_manager.Token;

public class SecretaryFacade {
    private InsertStudentsGradesSecretaryFacade insertStudentsGrades;

    public SecretaryFacade(Token token) {
        try{
            SessionManager.getInstance().getSessionUserByToken(token).getRole().secretary();
        }catch (MissingAuthorizationException e){

        }
    }

    public void InsertStudentsGrades(Token token){
        insertStudentsGrades = new InsertStudentsGradesSecretaryFacade(token);
    }
}
