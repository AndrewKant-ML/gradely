import it.uniroma2.dicii.ispw.gradely.dao_manager.DAOFactoryAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.SubjectCourseCodeEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.degree_course_enrollment.DegreeCourseEnrollment;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.degree_course_enrollment.DegreeCourseEnrollmentLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_assignment.SubjectCourseAssignment;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_assignment.SubjectCourseAssignmentLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.Professor;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.ProfessorLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;
import it.uniroma2.dicii.ispw.gradely.model.role.student.StudentLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;
import it.uniroma2.dicii.ispw.gradely.model.user.User;
import it.uniroma2.dicii.ispw.gradely.model.user.UserLazyFactory;
import org.junit.jupiter.api.Test;

import java.time.Year;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class DAODBTests {

    private static final Logger LOGGER = Logger.getLogger(DAODBTests.class.getName());

    @Test
    void testSubjectCourseDAO() throws UserNotFoundException, WrongListQueryIdentifierValue, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException, PropertyException, ResourceNotFoundException, DAOException, ObjectNotFoundException {
        SubjectCourseCodeEnum code = SubjectCourseCodeEnum.C01;
        String name = "ISPW";
        Integer cfu = 12;
        Year academicYear = Year.of(2022);
        SubjectCourse subjectCourse = DAOFactoryAbstract.getInstance().getSubjectCourseDAO().getSubjectCourseByNameCodeCfuAndAcademicYear(name, code, cfu, academicYear);

        assertNotNull(subjectCourse);
        assertEquals(subjectCourse.getName(), name);
        assertEquals(subjectCourse.getCode(), code);
        assertEquals(subjectCourse.getCfu(), cfu);
        assertEquals(subjectCourse.getAcademicYear(), academicYear);
    }

    @Test
    void testSubjectCourseAssignmentDAO() throws DAOException, UserNotFoundException, WrongListQueryIdentifierValue, ObjectNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException, PropertyException, ResourceNotFoundException {
        Professor professor = ProfessorLazyFactory.getInstance().getProfessorByUser(
                UserLazyFactory.getInstance().getUserByCodiceFiscale("FLX")
        );
        List<SubjectCourseAssignment> assignments = SubjectCourseAssignmentLazyFactory.getInstance().getCourseAssignmentsByProfessor(professor);

        assertNotNull(assignments);
        assertFalse(assignments.isEmpty());
    }

    @Test
    void testUserDAO() throws DAOException, UserNotFoundException, WrongListQueryIdentifierValue, ObjectNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException, PropertyException, ResourceNotFoundException {
        User user = UserLazyFactory.getInstance().getUserByCodiceFiscale("CNT");

        assertNotNull(user);
    }

    @Test
    void testDegreeCourseEnrollmentDAO() throws DAOException, UserNotFoundException, WrongListQueryIdentifierValue, ObjectNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException, PropertyException, ResourceNotFoundException {
        Student student = StudentLazyFactory.getInstance().getStudentByUser(UserLazyFactory.getInstance().getUserByCodiceFiscale("CNT"));
        List<DegreeCourseEnrollment> enrollments = DegreeCourseEnrollmentLazyFactory.getInstance().getDegreeCourseEnrollmentsByStudent(student);

        assertNotNull(enrollments);
    }
}
