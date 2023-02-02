package it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course;

import it.uniroma2.dicii.ispw.gradely.beans_general.DegreeCourseBean;
import it.uniroma2.dicii.ispw.gradely.beans_general.TestInfoBean;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.MissingAuthorizationException;
import it.uniroma2.dicii.ispw.gradely.exceptions.TestRetrivialException;
import it.uniroma2.dicii.ispw.gradely.session_manager.SessionManager;
import it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.beans.TestReservationBean;

import java.util.List;

public class EnrollToDegreeCourseStudentFacade {
    private EnrollToDegreeCourseController controller;

    public EnrollToDegreeCourseStudentFacade(String tokenKey) throws MissingAuthorizationException {
        SessionManager.getInstance().getSessionUserByTokenKey(tokenKey).getRole().castToStudentRole();
        controller = new EnrollToDegreeCourseController();
    }

    public List<DegreeCourseBean> getDegreeCourses(String tokenKey) throws MissingAuthorizationException, DAOException {
        return controller.getJoinableDegreeCourses(tokenKey);
    }

    public TestInfoBean getTestInfo(String tokenKey, DegreeCourseBean degreeCourseBean) throws TestRetrivialException, MissingAuthorizationException {
        SessionManager.getInstance().getSessionUserByTokenKey(tokenKey).getRole().castToStudentRole();
        return controller.getTestInfo(tokenKey, degreeCourseBean);
    }

    public TestReservationBean reserveTest(String tokenKey, TestInfoBean testInfo) throws MissingAuthorizationException {
        SessionManager.getInstance().getSessionUserByTokenKey(tokenKey).getRole().castToStudentRole();
        return controller.reserveTest(tokenKey, testInfo);
    }
}
