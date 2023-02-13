package it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.graphic_controllers;

import it.uniroma2.dicii.ispw.gradely.beans_general.DegreeCourseBean;
import it.uniroma2.dicii.ispw.gradely.beans_general.TestInfoBean;
import it.uniroma2.dicii.ispw.gradely.enums.UserErrorMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.loggers_general.GeneralLogger;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;
import it.uniroma2.dicii.ispw.gradely.session_manager.SessionManager;
import it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.EnrollToDegreeCourseStudentFacade;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EnrollToDegreeCourseCLIGraphicController {

    private final Logger logger = Logger.getLogger(EnrollToDegreeCourseCLIGraphicController.class.getName());
    private final Scanner scanner = new Scanner(System.in);
    private EnrollToDegreeCourseStudentFacade facade;
    private String tokenKey; // TBI ?

    private DegreeCourseBean selectedCourse;

    public EnrollToDegreeCourseCLIGraphicController(String tokenKey) {
        try {
            this.tokenKey = tokenKey;
            facade = new EnrollToDegreeCourseStudentFacade(tokenKey);
        } catch (MissingAuthorizationException e) {
            GeneralLogger.logSevere(UserErrorMessagesEnum.MISSING_AUTHORIZATION_MSG.message);
        }
    }

    public void initDegreeCourseEnrollment() {
        try {
            selectDegreeCourses();
            confirmAnagraphicalData();
            showTestInfo();
        } catch (DAOException | MissingAuthorizationException | TestRetrivialException | PropertyException |
                 ResourceNotFoundException | WrongDegreeCourseCodeException | UserNotFoundException |
                 WrongTimerTypeException | WrongListQueryIdentifierValue | UnrecognizedRoleException e) {
            GeneralLogger.logSevere(e.getMessage());
        }
    }

    private int selectDegreeCourses() throws DAOException, MissingAuthorizationException, WrongDegreeCourseCodeException {
        List<DegreeCourseBean> degreeCourseBeans = facade.getDegreeCourses(tokenKey);
        logger.log(Level.FINEST, "Select the degree course you want to enroll to:");
        for (int i = 0; i < degreeCourseBeans.size(); i++) {
            String option = String.format("%d) %s%n", i + 1, degreeCourseBeans.get(i).getName());
            logger.log(Level.FINEST, option);
        }
        String option = String.format("%d) Back%n", degreeCourseBeans.size());
        logger.log(Level.FINEST, option);
        while (!scanner.hasNextInt())
            GeneralLogger.logWarning(UserErrorMessagesEnum.SELECT_A_DEGREE_COURSE_MSG.message);
        int choice = scanner.nextInt();
        if (choice == degreeCourseBeans.size())
            return -1;
        selectedCourse = degreeCourseBeans.get(choice);
        return 1;
    }

    private int confirmAnagraphicalData() throws MissingAuthorizationException {
        // TBI use UserData bean
        Student student = SessionManager.getInstance().getSessionUserByTokenKey(this.tokenKey).getRole().getStudentRole();
        String studentName = String.format("Name and surname: %s %s%n", student.getUser().getName(), student.getUser().getSurname());
        String matricola = String.format("Matricola: %s%n", student.getMatricola());
        String codiceFiscale = String.format("Codice fiscale: %s%n", student.getUser().getCodiceFiscale());
        String email = String.format("Email: %s%n", student.getUser().getEmail());
        logger.log(Level.FINEST, studentName);
        logger.log(Level.FINEST, matricola);
        logger.log(Level.FINEST, codiceFiscale);
        logger.log(Level.FINEST, email);
        logger.log(Level.FINEST, "1) Confirm%n2)Change data (Under construction)%n3) Back");
        int choice;
        do {
            while (!scanner.hasNextInt())
                GeneralLogger.logWarning(UserErrorMessagesEnum.SELECT_A_DEGREE_COURSE_MSG.message);
            choice = scanner.nextInt();
        } while (choice != 1 && choice != 3);
        if (choice == 1)
            return 1;
        return -1;
    }

    private void showTestInfo() throws DAOException, TestRetrivialException, PropertyException, ResourceNotFoundException, MissingAuthorizationException, WrongDegreeCourseCodeException, UserNotFoundException, WrongTimerTypeException, WrongListQueryIdentifierValue, UnrecognizedRoleException {
        TestInfoBean testInfo = facade.getTestInfo(tokenKey, selectedCourse);
        String courseName = String.format("Degree course: %s%n", selectedCourse.getName());
        String testId = String.format("Test ID: %s%n", testInfo.getId());
        String testDate = String.format("Test date: %s%n", testInfo.getTestDate().toString());
        String testReservationLink = String.format("Test reservation link: %s%n", testInfo.getTestReservationLink().toString());
        String place = String.format("Place: %s%n", testInfo.getPlace());
        String resultsDate = String.format("Results date: %s%n", testInfo.getResultsDate().toString());
        String infoLink = String.format("Info link: %s%n", testInfo.getInfoLink().toString());
        logger.log(Level.FINEST, courseName);
        logger.log(Level.FINEST, testId);
        logger.log(Level.FINEST, testDate);
        logger.log(Level.FINEST, testReservationLink);
        logger.log(Level.FINEST, place);
        logger.log(Level.FINEST, resultsDate);
        logger.log(Level.FINEST, infoLink);
    }
}
