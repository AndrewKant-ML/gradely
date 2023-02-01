package it.uniroma2.dicii.ispw.gradely.facades;

import it.uniroma2.dicii.ispw.gradely.exceptions.MissingAuthorizationException;
import it.uniroma2.dicii.ispw.gradely.session_manager.SessionManager;
import it.uniroma2.dicii.ispw.gradely.session_manager.Token;
import it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.EnrollToDegreeCourseStudentFacade;
import it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.InsertStudentsGradesStudentFacade;

public class StudentFacade {
    private InsertStudentsGradesStudentFacade insertStudentsGrades;
    private EnrollToDegreeCourseStudentFacade enrollToDegreeCourse;

    public StudentFacade(Token token) throws MissingAuthorizationException{
        SessionManager.getInstance().getSessionUserByToken(token).getRole().getStudentRole();
    }

    public void insertStudentsGrades(Token token) throws MissingAuthorizationException{
        insertStudentsGrades = new InsertStudentsGradesStudentFacade(token);
    }
    public void enrollToDegreeCourse(Token token) throws MissingAuthorizationException{
        enrollToDegreeCourse = new EnrollToDegreeCourseStudentFacade(token);
    }

}
