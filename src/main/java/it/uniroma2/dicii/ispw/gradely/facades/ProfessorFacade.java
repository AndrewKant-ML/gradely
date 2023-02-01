package it.uniroma2.dicii.ispw.gradely.facades;

import it.uniroma2.dicii.ispw.gradely.exceptions.MissingAuthorizationException;
import it.uniroma2.dicii.ispw.gradely.session_manager.SessionManager;
import it.uniroma2.dicii.ispw.gradely.session_manager.Token;
import it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.InsertStudentsGradesProfessorFacade;

public class ProfessorFacade {
    private InsertStudentsGradesProfessorFacade insertStudentsGrades;

    public ProfessorFacade(Token token) throws MissingAuthorizationException{
        SessionManager.getInstance().getSessionUserByToken(token).getRole().getProfessorRole();
    }

    public void InsertStudentsGrades(Token token) throws MissingAuthorizationException{
        insertStudentsGrades = new InsertStudentsGradesProfessorFacade(token);
    }
}
