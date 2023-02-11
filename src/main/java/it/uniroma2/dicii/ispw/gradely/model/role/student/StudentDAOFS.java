package it.uniroma2.dicii.ispw.gradely.model.role.student;

import it.uniroma2.dicii.ispw.gradely.CSVParser;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.degree_course_enrollment.DegreeCourseEnrollmentLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment.ExamEnrollmentLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_enrollment.SubjectCourseEnrollmentLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.title.TitleLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.util.List;

public class StudentDAOFS implements StudentDAOInterface {

    private static StudentDAOFS instance;

    private final String filename = "student";

    private StudentDAOFS() {

    }

    public static synchronized StudentDAOInterface getInstance() {
        if (instance == null) {
            instance = new StudentDAOFS();
        }
        return instance;
    }

    @Override
    public Student getStudentByUser(User user) throws DAOException, UserNotFoundException, ResourceNotFoundException, PropertyException {
        /*try {
            List<List<String>> lines = new CSVParser().readAllLines(filename);
            for (List<String> line : lines) {
                if (line.get(0).equals(user.getCodiceFiscale())) {
                    Student student = new Student(
                            user,
                            line.get(1)
                    );
                    student.setDegreeCourseEnrollments(DegreeCourseEnrollmentLazyFactory.getInstance().getDegreeCourseEnrollmentsByStudent(student));
                    student.setTitles(TitleLazyFactory.getInstance().getTitlesByStudent(student));
                    student.setExamEnrollments(ExamEnrollmentLazyFactory.getInstance().getExamEnrollmentsByStudent(student));
                    student.setSubjectCourseEnrollments(SubjectCourseEnrollmentLazyFactory.getInstance().getSubjectCourseEnrollmentsByStudent(student));
                    return student;
                }
            }
            throw new UserNotFoundException(ExceptionMessagesEnum.SECRETARY_NOT_FOUND.message);
        } catch (CsvException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }*/
        return null;
    }

    @Override
    public void insert(Student student){

    }

    @Override
    public void cancel(Student student){

    }

    @Override
    public void update(Student student){

    }

}
