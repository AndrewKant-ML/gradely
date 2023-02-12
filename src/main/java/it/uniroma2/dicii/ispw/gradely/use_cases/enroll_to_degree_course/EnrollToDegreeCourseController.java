package it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course;

import it.uniroma2.dicii.ispw.gradely.beans_general.DegreeCourseBean;
import it.uniroma2.dicii.ispw.gradely.beans_general.TestInfoBean;
import it.uniroma2.dicii.ispw.gradely.enums.TestTypeEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.test_reservation.TestReservationLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourse;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourseLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;
import it.uniroma2.dicii.ispw.gradely.model.test.Test;
import it.uniroma2.dicii.ispw.gradely.model.test.TestLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.timer.AbstractTimer;
import it.uniroma2.dicii.ispw.gradely.model.timer.TestResultTimer;
import it.uniroma2.dicii.ispw.gradely.model.timer.TimerLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.timer.TimerObserver;
import it.uniroma2.dicii.ispw.gradely.model.title.Title;
import it.uniroma2.dicii.ispw.gradely.session_manager.SessionManager;
import it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.beans.TestReservationBean;
import it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.external_boundaries.AbstractTestBoundary;
import it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.factory.AbstractTestFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EnrollToDegreeCourseController extends TimerObserver {

    private final Logger logger = Logger.getLogger(EnrollToDegreeCourseController.class.getName());

    public EnrollToDegreeCourseController() {
        super();
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
     * @throws MissingAuthorizationException thrown if the User has no authorization to execute the requested operation OR thrown when the requesting user is not a student
     * @throws DAOException                  thrown when problems occur while retrieving data from persistence
     */
    public List<DegreeCourseBean> getJoinableDegreeCourses(String tokenKey) throws MissingAuthorizationException, DAOException, WrongDegreeCourseCodeException {
        Student student = SessionManager.getInstance().getSessionUserByTokenKey(tokenKey).getRole().getStudentRole();
        List<DegreeCourse> degreeCourses = DegreeCourseLazyFactory.getInstance().getAllDegreeCourses();
        // Removes degree courses already enrolled to by the student
        student.getDegreeCourseEnrollments().forEach(
                degreeCourseEnrollment -> degreeCourses.remove(degreeCourseEnrollment.getDegreeCourse())
        );
        // Removes degree courses if student has already achieved that title
        degreeCourses.removeIf(
                degreeCourse -> {
                    for (Title title : student.getTitles())
                        if (title.getDegreeCourse().getCode().equals(degreeCourse.getCode()))
                            return true;
                    return false;
                }
        );
        // TBI check prerequisites
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
    public TestInfoBean getTestInfo(String tokenKey, DegreeCourseBean degreeCourseBean) throws TestRetrivialException, MissingAuthorizationException, PropertyException, ResourceNotFoundException, DAOException, WrongDegreeCourseCodeException {
        SessionManager.getInstance().getSessionUserByTokenKey(tokenKey).getRole().getStudentRole();
        AbstractTestBoundary testBoundary = AbstractTestFactory.getInstance(degreeCourseBean.getTestType()).createTestBoundary();
        TestInfoBean testInfo = testBoundary.getTestInfo();
        Test savedTest;
        try {
            savedTest = TestLazyFactory.getInstance().saveTestData(
                    DegreeCourseLazyFactory.getInstance().getDegreeCourseByName(degreeCourseBean.getName()),
                    testInfo.getId(),
                    testInfo.getTestDate(),
                    testInfo.getTestReservationLink(),
                    testInfo.getResultsDate(),
                    testInfo.getInfoLink(),
                    testInfo.getPlace()
            );
        } catch (ObjectNotFoundException e) {
            // This can only happen if DB is corrupted, so the application must stop
            logger.log(Level.SEVERE, String.format("Error: degree course with name %s does not exists", degreeCourseBean.getName()));
            System.exit(1);
        }
        testInfo.setTestType(degreeCourseBean.getTestType().value);
        return testInfo;
    }

    /**
     * Reserves to a given test
     *
     * @param tokenKey the token key used to grant operation authorization
     * @param testInfo the test to reserve to
     * @return a TestReservationBean containing all the reservation info
     * @throws MissingAuthorizationException thrown if the User has no authorization to execute the requested operation OR thrown if the token-relative User has no authorization to execute this operation
     */
    public TestReservationBean reserveTest(String tokenKey, TestInfoBean testInfo) throws MissingAuthorizationException, DAOException, PropertyException, ResourceNotFoundException, WrongDegreeCourseCodeException {
        Student student = SessionManager.getInstance().getSessionUserByTokenKey(tokenKey).getRole().getStudentRole();
        AbstractTestBoundary testBoundary = AbstractTestFactory.getInstance(TestTypeEnum.getTestTypeByValue(testInfo.getTestType())).createTestBoundary();
        TestReservationBean testReservation = testBoundary.reserveTest(testInfo.getId());
        try {
            TestReservationLazyFactory.getInstance().reserveTest(student, TestLazyFactory.getInstance().getTestById(testInfo.getId()));
        } catch (ObjectNotFoundException e) {
            // This can only happen if DB is corrupted, so the application must stop
            logger.log(Level.SEVERE, String.format("Error: test with id %s does not exists", testInfo.getId()));
            System.exit(1);
        }
        return testReservation;
    }

    @Override
    public void timeIsUp(AbstractTimer timer) throws WrongTimerTypeException {
        TestResultTimer concreteTimer = timer.castToTestResultTimer();

    }
}
