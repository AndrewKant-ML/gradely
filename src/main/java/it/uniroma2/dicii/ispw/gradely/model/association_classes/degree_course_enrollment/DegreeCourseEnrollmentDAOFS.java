package it.uniroma2.dicii.ispw.gradely.model.association_classes.degree_course_enrollment;

import com.opencsv.exceptions.CsvException;
import it.uniroma2.dicii.ispw.gradely.CSVParser;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourse;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourseLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;
import it.uniroma2.dicii.ispw.gradely.model.user.UserLazyFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DegreeCourseEnrollmentDAOFS extends AbstractDegreeCourseEnrollmentDAO {

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
        try {
            List<List<String>> lines = new CSVParser().readAllLines(fileName);
            List<DegreeCourseEnrollment> enrollments = new ArrayList<>();
            for (List<String> line : lines) {
                if (Integer.parseInt(line.get(0)) == course.getCode().value && line.get(1).equals(course.getName()))
                    enrollments.add(new DegreeCourseEnrollment(
                            LocalDate.parse(line.get(3)),
                            UserLazyFactory.getInstance().getUserByCodiceFiscale(line.get(2)).getRole().castToStudentRole(),
                            course
                    ));
            }
            return enrollments;
        } catch (CsvException | UserNotFoundException | MissingAuthorizationException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    @Override
    public List<DegreeCourseEnrollment> getDegreeCourseEnrollmentsByStudent(Student student) throws DAOException, ResourceNotFoundException {
        try {
            List<List<String>> lines = new CSVParser().readAllLines(fileName);
            List<DegreeCourseEnrollment> enrollments = new ArrayList<>();
            for (List<String> line : lines) {
                if (line.get(2).equals(student.getUser().getCodiceFiscale()))
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

    @Override
    public void insert(DegreeCourseEnrollment degreeCourseEnrollment){

    }

    @Override
    public void cancel(DegreeCourseEnrollment degreeCourseEnrollment){

    }

    @Override
    public void update(DegreeCourseEnrollment degreeCourseEnrollment){

    }


}