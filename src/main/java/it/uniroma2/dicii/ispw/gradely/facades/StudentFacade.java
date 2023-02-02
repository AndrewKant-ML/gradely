package it.uniroma2.dicii.ispw.gradely.facades;

import it.uniroma2.dicii.ispw.gradely.exceptions.MissingAuthorizationException;
import it.uniroma2.dicii.ispw.gradely.session_manager.SessionManager;
import it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.EnrollToDegreeCourseStudentFacade;
import it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.InsertStudentsGradesStudentFacade;

public class StudentFacade {
    private InsertStudentsGradesStudentFacade insertStudentsGrades;
    private EnrollToDegreeCourseStudentFacade enrollToDegreeCourse;

    public StudentFacade(String tokenKey) throws MissingAuthorizationException{
        SessionManager.getInstance().getSessionUserByTokenKey(tokenKey).getRole().castToStudentRole();
    }

    public void insertStudentsGrades(String tokenKey) throws MissingAuthorizationException{
        insertStudentsGrades = new InsertStudentsGradesStudentFacade(tokenKey);
    }
    public void enrollToDegreeCourse(String tokenKey) throws MissingAuthorizationException{
        enrollToDegreeCourse = new EnrollToDegreeCourseStudentFacade(tokenKey);
    }

}
