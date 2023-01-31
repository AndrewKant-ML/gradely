package it.uniroma2.dicii.ispw.gradely.facades.student;

import it.uniroma2.dicii.ispw.gradely.exceptions.MissingAuthorizationException;
import it.uniroma2.dicii.ispw.gradely.facades.professor.InsertStudentsGradesProfessorFacade;
import it.uniroma2.dicii.ispw.gradely.session_manager.SessionManager;
import it.uniroma2.dicii.ispw.gradely.session_manager.Token;

public class StudentFacade {
    private InsertStudentsGradesStudentFacade insertStudentsGrades;
    private EnrollToDegreeCourseStudentFacade enrollToDegreeCourse;

    public StudentFacade(Token token) {
        try{
            SessionManager.getInstance().getSessionUserByToken(token).getRole().student();
        }catch (MissingAuthorizationException e){

        }
    }

    public void insertStudentsGrades(Token token){
        insertStudentsGrades = new InsertStudentsGradesStudentFacade(token);
    }
    public void enrollToDegreeCourse(Token token){
        enrollToDegreeCourse = new EnrollToDegreeCourseStudentFacade(token);
    }

}
