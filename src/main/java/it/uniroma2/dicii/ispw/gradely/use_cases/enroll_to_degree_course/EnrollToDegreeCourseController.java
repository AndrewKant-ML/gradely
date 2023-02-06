package it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course;

import it.uniroma2.dicii.ispw.gradely.beans_general.DegreeCourseBean;
import it.uniroma2.dicii.ispw.gradely.beans_general.TestInfoBean;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.MissingAuthorizationException;
import it.uniroma2.dicii.ispw.gradely.exceptions.TestRetrivialException;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourse;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourseLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;
import it.uniroma2.dicii.ispw.gradely.model.timer.AbstractTimer;
import it.uniroma2.dicii.ispw.gradely.model.timer.TimerObserver;
import it.uniroma2.dicii.ispw.gradely.model.title.Title;
import it.uniroma2.dicii.ispw.gradely.session_manager.SessionManager;
import it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.beans.TestReservationBean;
import it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.external_boundaries.AbstractTestBoundary;
import it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.factory.AbstractTestFactory;

import java.util.ArrayList;
import java.util.List;

public class EnrollToDegreeCourseController implements TimerObserver {

    private AbstractTestBoundary testBoundary;

    public EnrollToDegreeCourseController() {

    }

    /**
     * Get all student-joinable degree courses.
     * Courses are filtered basing on:
     * <ul>
     *     <li>
     *         courses to which the student has already joined
     *     </li>
     *     <li>
     *         courses which requires a Titolo not possessed by the student
     *     </li>
     * </ul>
     *
     * @param tokenKey the student-relative token
     * @return a list of student-joinable degree courses
     * @throws MissingAuthorizationException thrown when the requesting user is not a student
     * @throws DAOException                  thrown when problems occur while retrieving data from persistence
     */
    public List<DegreeCourseBean> getJoinableDegreeCourses(String tokenKey) throws MissingAuthorizationException, DAOException {
        Student student = SessionManager.getInstance().getSessionUserByTokenKey(tokenKey).getRole().castToStudentRole();
        List<DegreeCourse> degreeCourses = DegreeCourseLazyFactory.getInstance().getAllDegreeCourses();
        student.getDegreeCourseEnrollments().forEach(
                degreeCourseEnrollment -> degreeCourses.remove(degreeCourseEnrollment.getDegreeCourse())
        );
        degreeCourses.removeIf(
                degreeCourse -> {
                    for (Title title : student.getTitles())
                        if (title.getDegreeCourse().getCode().equals(degreeCourse.getCode()))
                            return true;
                    return false;
                }
        );
        List<DegreeCourseBean> beans = new ArrayList<>();
        for (DegreeCourse degreeCourse : degreeCourses) {
            beans.add(
                    new DegreeCourseBean(degreeCourse.getName(), degreeCourse.getFacolta(), degreeCourse.getType(), degreeCourse.getTestType())
            );
        }
        return beans;
    }

    /**
     * Creates a boundary to the external test system and retrieves test info.
     * The created boundary has a runtime-type according to the external test
     * system, i.e.:
     * - Moodle -> TestType.ONLINE
     * - MUR -> TestType.MUR
     *
     * @param tokenKey         the token key
     * @param degreeCourseBean the degree course referred from the test
     * @return the test info
     */
    public TestInfoBean getTestInfo(String tokenKey, DegreeCourseBean degreeCourseBean) throws TestRetrivialException, MissingAuthorizationException {
        SessionManager.getInstance().getSessionUserByTokenKey(tokenKey).getRole().castToStudentRole();
        // TODO save degree course test type and pass reservation code to boundary for test results
        this.testBoundary = AbstractTestFactory.getInstance(degreeCourseBean.getTestType()).createTestBoundary();
        return testBoundary.getTestInfo();
    }

    public TestReservationBean reserveTest(String tokenKey, TestInfoBean testInfo) throws MissingAuthorizationException {
        SessionManager.getInstance().getSessionUserByTokenKey(tokenKey).getRole().castToStudentRole();
        return this.testBoundary.reserveTest(testInfo.getId());
    }

    @Override
    public void timeIsUp(AbstractTimer timer) {

    }
}
