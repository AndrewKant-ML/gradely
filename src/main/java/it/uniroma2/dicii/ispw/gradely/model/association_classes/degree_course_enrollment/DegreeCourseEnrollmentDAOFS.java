package it.uniroma2.dicii.ispw.gradely.model.association_classes.degree_course_enrollment;

import com.opencsv.exceptions.CsvException;
import it.uniroma2.dicii.ispw.gradely.CSVParser;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ObjectNotFoundException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourse;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourseLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DegreeCourseEnrollmentDAOFS implements AbstractDegreeCourseEnrollmentDAO {

    private static DegreeCourseEnrollmentDAOFS instance;
    private final String fileName = "degree_course_enrollment";

    private DegreeCourseEnrollmentDAOFS() {

    }

    public static synchronized AbstractDegreeCourseEnrollmentDAO getInstance() {
        if (instance == null) {
            instance = new DegreeCourseEnrollmentDAOFS();
        }
        return instance;
    }

    @Override
    public List<DegreeCourseEnrollment> getDegreeCourseEnrollmentsByDegreeCourse(DegreeCourse course) throws DAOException, ResourceNotFoundException, PropertyException {
        /*try {
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
        }*/
        return null;
    }

    @Override
    public List<DegreeCourseEnrollment> getDegreeCourseEnrollmentsByStudent(Student student) throws DAOException, ResourceNotFoundException {
        try {
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
        }
    }

    public void insert(DegreeCourseEnrollment degreeCourseEnrollment){

    }

    public void cancel(DegreeCourseEnrollment degreeCourseEnrollment){

    }

    public void update(DegreeCourseEnrollment degreeCourseEnrollment){

    }
}