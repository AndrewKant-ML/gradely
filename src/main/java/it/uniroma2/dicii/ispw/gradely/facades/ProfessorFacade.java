package it.uniroma2.dicii.ispw.gradely.facades;

import it.uniroma2.dicii.ispw.gradely.exceptions.MissingAuthorizationException;
import it.uniroma2.dicii.ispw.gradely.session_manager.SessionManager;
import it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.InsertStudentsGradesProfessorFacade;

public class ProfessorFacade {
    private InsertStudentsGradesProfessorFacade insertStudentsGrades;

    public ProfessorFacade(String tokenKey) throws MissingAuthorizationException{
        SessionManager.getInstance().getSessionUserByTokenKey(tokenKey).getRole().castToProfessorRole();
    }

    public void insertStudentsGrades(String tokenKey) throws MissingAuthorizationException{
        insertStudentsGrades = new InsertStudentsGradesProfessorFacade(tokenKey);
    }


}
