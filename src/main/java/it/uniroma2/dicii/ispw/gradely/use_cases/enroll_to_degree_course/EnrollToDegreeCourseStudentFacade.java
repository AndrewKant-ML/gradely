package it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course;

import it.uniroma2.dicii.ispw.gradely.beans_general.DegreeCourseBean;
import it.uniroma2.dicii.ispw.gradely.beans_general.TestInfoBean;
import it.uniroma2.dicii.ispw.gradely.exceptions.MissingAuthorizationException;
import it.uniroma2.dicii.ispw.gradely.exceptions.TestRetrivialException;
import it.uniroma2.dicii.ispw.gradely.session_manager.SessionManager;
import it.uniroma2.dicii.ispw.gradely.session_manager.Token;
import it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.beans.TestReservationBean;

import java.util.List;

public class EnrollToDegreeCourseStudentFacade {
    private EnrollToDegreeCourseController controller;

    public EnrollToDegreeCourseStudentFacade(Token token){
        try{
            SessionManager.getInstance().getSessionUserByToken(token).getRole().getStudentRole();
            controller = new EnrollToDegreeCourseController();
        }catch (MissingAuthorizationException e){

        }
    }

    public List<DegreeCourseBean> getDegreeCourses (Token token) throws MissingAuthorizationException{
        return controller.getDegreeCourses(token);
    }

    public TestInfoBean getTestInfo(Token token, DegreeCourseBean degreeCourseBean) throws TestRetrivialException, MissingAuthorizationException {
        SessionManager.getInstance().getSessionUserByToken(token).getRole().getStudentRole();
        return controller.getTestInfo(token, degreeCourseBean);
    }

    public TestReservationBean reserveTest(Token token, TestInfoBean testInfo) throws MissingAuthorizationException{
        SessionManager.getInstance().getSessionUserByToken(token).getRole().getStudentRole();
        return controller.reserveTest(token, testInfo);
    }

}
