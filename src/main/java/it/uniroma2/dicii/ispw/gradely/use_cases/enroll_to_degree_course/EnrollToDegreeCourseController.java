package it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course;

import it.uniroma2.dicii.ispw.gradely.PageNavigationController;
import it.uniroma2.dicii.ispw.gradely.beans_general.DegreeCourseBean;
import it.uniroma2.dicii.ispw.gradely.beans_general.StudentBean;
import it.uniroma2.dicii.ispw.gradely.beans_general.TestInfoBean;
import it.uniroma2.dicii.ispw.gradely.beans_general.UserBean;
import it.uniroma2.dicii.ispw.gradely.exceptions.TestRetrivialException;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourse;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourseLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.timer.AbstractTimer;
import it.uniroma2.dicii.ispw.gradely.model.timer.TimerObserver;
import it.uniroma2.dicii.ispw.gradely.model.user.User;
import it.uniroma2.dicii.ispw.gradely.session_manager.SessionManager;
import it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.beans.TestReservationBean;
import it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.external_boundaries.AbstractTestBoundary;
import it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.factory.AbstractTestFactory;

import java.util.ArrayList;
import java.util.List;

public class EnrollToDegreeCourseController implements TimerObserver {

    private final StudentBean studentBean;
    private final UserBean userBean;
    private AbstractTestBoundary testBoundary;

    public EnrollToDegreeCourseController() {
        User user = SessionManager.getInstance().getSessionUserByToken(
                PageNavigationController.getInstance().getSessionToken()
        );
        this.studentBean = new StudentBean(user.getRole().student().getId());
        this.userBean = new UserBean(
                user.getName(),
                user.getSurname(),
                user.getCodiceFiscale(),
                user.getEmail()
        );
    }

    public List<DegreeCourseBean> getDegreeCourses() {
        List<DegreeCourse> degreeCourses = DegreeCourseLazyFactory.getInstance().getDegreeCourses();
        List<DegreeCourseBean> beans = new ArrayList<>();
        for (DegreeCourse degreeCourse : degreeCourses) {
            beans.add(
                    new DegreeCourseBean(degreeCourse.getName(), degreeCourse.getFacolta(), degreeCourse.getType(), degreeCourse.getTestType())
            );
        }
        return beans;
    }

    public StudentBean getStudentBean() {
        return studentBean;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    /**
     * Creates a boundary to the external test system and retrieves test info.
     * The created boundary has a runtime-type according to the external test
     * system, i.e.:
     * - Moodle -> TestType.ONLINE
     * - MUR -> TestType.MUR
     *
     * @param degreeCourseBean the degree course referred from the test
     * @return the test info
     */
    public TestInfoBean getTestInfo(DegreeCourseBean degreeCourseBean) throws TestRetrivialException {
        this.testBoundary = AbstractTestFactory.getInstance(degreeCourseBean.getTestType()).createTestBoundary();
        return testBoundary.getTestInfo();
    }

    public TestReservationBean reserveTest(TestInfoBean testInfo) {
        return this.testBoundary.reserveTest(testInfo.getID());
    }

    @Override
    public void timeIsUp(AbstractTimer timer) {

    }
}
