package it.uniroma2.dicii.ispw.gradely.model.association_classes.degree_course_enrollment;

import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourse;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;

import java.util.List;

public class DegreeCourseEnrollmentDAOFS implements DegreeCourseEnrollmentDAOInterface {

    private static DegreeCourseEnrollmentDAOFS instance;
    private static final String fileName = "degree_course_enrollment";

    private DegreeCourseEnrollmentDAOFS() {

    }

    public static synchronized DegreeCourseEnrollmentDAOInterface getInstance() {
        if (instance == null) {
            instance = new DegreeCourseEnrollmentDAOFS();
        }
        return instance;
    }

    @Override
    public List<DegreeCourseEnrollment> getDegreeCourseEnrollmentsByDegreeCourse(DegreeCourse course, List<DegreeCourseEnrollment> excluded) throws DAOException, PropertyException, ResourceNotFoundException, UserNotFoundException, WrongListQueryIdentifierValue, ObjectNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException {
        try {
            List<List<String>> lines = new CSVParser().readAllLines(fileName);
            List<DegreeCourseEnrollment> enrollments = new ArrayList<>();
            for (List<String> line : lines) {
                if (Integer.parseInt(line.get(0)) == course.getCode().value && line.get(1).equals(course.getName()))
                    enrollments.add(new DegreeCourseEnrollment(
                            LocalDate.parse(line.get(3)),
                            UserLazyFactory.getInstance().getUserByCodiceFiscale(line.get(2)).getRole().getStudentRole(),
                            course
                    ));
            }
            return enrollments;
        } catch (CsvException | UserNotFoundException | MissingAuthorizationException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
        return null;
    }

    @Override
    public List<DegreeCourseEnrollment> getDegreeCourseEnrollmentsByStudent(Student student, List<DegreeCourseEnrollment> exclusions) throws DAOException, PropertyException, ResourceNotFoundException, WrongDegreeCourseCodeException, UserNotFoundException, WrongListQueryIdentifierValue, ObjectNotFoundException, UnrecognizedRoleException, MissingAuthorizationException {
        /*try {
            List<List<String>> lines = new CSVParser().readAllLines(fileName);
            List<DegreeCourseEnrollment> enrollments = new ArrayList<>();
            for (List<String> line : lines) {
                if (line.get(2).equals(student.getCodiceFiscale()))
                    enrollments.add(new DegreeCourseEnrollment(
                            LocalDate.parse(line.get(3)),
                            student,
                            DegreeCourseLazyFactory.getInstance().getDegreeCourseByName(line.get(1))
                    ));
            }
            return enrollments;
        } catch (CsvException | ObjectNotFoundException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }*/
        return null;
    }

    public void insert(DegreeCourseEnrollment degreeCourseEnrollment){
        // To be implemented
    }

    public void delete(DegreeCourseEnrollment degreeCourseEnrollment){
        // To be implemented
    }

    public void update(DegreeCourseEnrollment degreeCourseEnrollment){
        // To be implemented
    }
}