package it.uniroma2.dicii.ispw.gradely.facades.professor;

import it.uniroma2.dicii.ispw.gradely.exceptions.MissingAuthorizationException;
import it.uniroma2.dicii.ispw.gradely.session_manager.SessionManager;
import it.uniroma2.dicii.ispw.gradely.session_manager.Token;

public class ProfessorFacade {
    private InsertStudentsGradesProfessorFacade insertStudentsGrades;

    public ProfessorFacade(Token token) {
        try{
            SessionManager.getInstance().getSessionUserByToken(token).getRole().professor();
        }catch (MissingAuthorizationException e){

        }
    }

    public void InsertStudentsGrades(Token token){
        insertStudentsGrades = new InsertStudentsGradesProfessorFacade(token);
    }
}
