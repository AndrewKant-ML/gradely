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

public class EnrollToDegreeCourseCLIGraphicController {

    private Scanner scanner = new Scanner(System.in);

    private EnrollToDegreeCourseStudentFacade facade;
    private String tokenKey; // TODO ?

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
        } catch (DAOException e) {
            throw new RuntimeException(e);
        } catch (MissingAuthorizationException e) {
            throw new RuntimeException(e);
        } catch (TestRetrivialException e) {
            throw new RuntimeException(e);
        } catch (PropertyException e) {
            throw new RuntimeException(e);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private int selectDegreeCourses() throws DAOException, MissingAuthorizationException {
        List<DegreeCourseBean> degreeCourseBeans = facade.getDegreeCourses(tokenKey);
        System.out.println("Select the degree course you want to enroll to:");
        for (int i = 0; i < degreeCourseBeans.size(); i++)
            System.out.printf("%d) %s\n", i + 1, degreeCourseBeans.get(i).getName());
        System.out.printf("%d) Back\n", degreeCourseBeans.size());
        while (!scanner.hasNextInt())
            GeneralLogger.logWarning(UserErrorMessagesEnum.SELECT_A_DEGREE_COURSE_MSG.message);
        int choice = scanner.nextInt();
        if (choice == degreeCourseBeans.size())
            return -1;
        selectedCourse = degreeCourseBeans.get(choice);
        return 1;
    }

    private int confirmAnagraphicalData() throws MissingAuthorizationException {
        // TODO use UserData bean
        Student student = SessionManager.getInstance().getSessionUserByTokenKey(this.tokenKey).getRole().getStudentRole();
        System.out.printf("Name and surname: %s %s\n", student.getUser().getName(), student.getUser().getSurname());
        System.out.printf("Matricola: %s\n", student.getMatricola());
        System.out.printf("Codice fiscale: %s\n", student.getUser().getCodiceFiscale());
        System.out.printf("Email: %s\n", student.getUser().getEmail());
        System.out.println("1) Confirm\n2)Change data (Under construction)\n3) Back");
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

    private void showTestInfo() throws DAOException, TestRetrivialException, PropertyException, ResourceNotFoundException, MissingAuthorizationException {
        TestInfoBean testInfo = facade.getTestInfo(tokenKey, selectedCourse);
        System.out.printf("Degree course: %s\n", selectedCourse.getName());
        System.out.printf("Test ID: %s\n", testInfo.getId());
        //System.out.printf("Test date: %s\n", testInfo.getTestDate().);
        System.out.printf("Degree course: %s\n", selectedCourse.getName());
        System.out.printf("Degree course: %s\n", selectedCourse.getName());
        System.out.printf("Degree course: %s\n", selectedCourse.getName());
    }
}
