package it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course;

import it.uniroma2.dicii.ispw.gradely.PageNavigationController;
import it.uniroma2.dicii.ispw.gradely.general_beans.DegreeCourseBean;
import it.uniroma2.dicii.ispw.gradely.general_beans.StudentBean;
import it.uniroma2.dicii.ispw.gradely.general_beans.TestInfoBean;
import it.uniroma2.dicii.ispw.gradely.general_beans.UserBean;
import it.uniroma2.dicii.ispw.gradely.model.User;
import it.uniroma2.dicii.ispw.gradely.session_manager.SessionManager;
import it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.boundary.AbstractTestBoundary;
import it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.factory.AbstractTestFactory;

import java.util.ArrayList;
import java.util.List;

public class EnrollToDegreeCourseControl {

    private final StudentBean studentBean;
    private final UserBean userBean;

    public EnrollToDegreeCourseControl() {
        User user = SessionManager.getInstance().getSessionUserByToken(
                PageNavigationController.getInstance().getSessionToken()
        );
        this.studentBean = new StudentBean(user.getRole().Student().getId());
        this.userBean = new UserBean(
                user.getName(),
                user.getSurname(),
                user.getCodiceFiscale(),
                user.getEmail()
        );
    }

    public List<DegreeCourseBean> getDegreeCourses() {
        // TODO call to lazyfactory method
        return new ArrayList<>();
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
    public TestInfoBean getTestInfo(DegreeCourseBean degreeCourseBean) {
        AbstractTestBoundary testBoundary = AbstractTestFactory.getInstance(degreeCourseBean.getTestTypeEnum()).createTestBoundary();
        return testBoundary.getTestInfo();
    }
}
