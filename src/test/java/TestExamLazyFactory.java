import it.uniroma2.dicii.ispw.gradely.enums.AppelloEnum;
import it.uniroma2.dicii.ispw.gradely.enums.SessionEnum;
import it.uniroma2.dicii.ispw.gradely.enums.SubjectCourseCodeEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.exam.Exam;
import it.uniroma2.dicii.ispw.gradely.model.exam.ExamLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourseLazyFactory;
import org.junit.jupiter.api.Test;

import java.time.Year;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Giuseppe Valerio Cantone
 */
class TestExamLazyFactory {

    @Test
    void TestGetExamsBySubjectCourse() throws DAOException, UserNotFoundException, PropertyException, WrongListQueryIdentifierValue, ObjectNotFoundException, ResourceNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException {
        ExamLazyFactory TestGetExamsBySubjectCourse = ExamLazyFactory.getInstance();
        SubjectCourse course = SubjectCourseLazyFactory.getInstance().getSubjectCourseByCodeNameCfuAndAcademicYear(
                SubjectCourseCodeEnum.C01,
                "ISPW",
                12,
                Year.of(2022)
        );
        List<Exam> list = TestGetExamsBySubjectCourse.getExamsBySubjectCourse(course);
        assertNotNull(list.get(0));
        assertEquals(list.get(0).getSubjectCourse().getCode().value,SubjectCourseCodeEnum.C01.value);
        assertEquals("ISPW",list.get(0).getSubjectCourse().getName());
        assertEquals(12,list.get(0).getSubjectCourse().getCfu());
        assertEquals(list.get(0).getSubjectCourse().getAcademicYear(),Year.of(2022));
    }

    @Test
    void TestGetExamByAppelloCourseAndSession() throws DAOException, UserNotFoundException, PropertyException, WrongListQueryIdentifierValue, ObjectNotFoundException, ResourceNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException {
        ExamLazyFactory TestGetExamByAppelloCourseAndSession = ExamLazyFactory.getInstance();
        SubjectCourse course = SubjectCourseLazyFactory.getInstance().getSubjectCourseByCodeNameCfuAndAcademicYear(
                SubjectCourseCodeEnum.C01,
                "ISPW",
                12,
                Year.of(2022)
        );
        Exam exam = TestGetExamByAppelloCourseAndSession.getExamByAppelloCourseAndSession(
                AppelloEnum.A2,
                course,
                SessionEnum.INVERNALE
        );
        assertNotNull(exam);
        assertEquals(exam.getAppello().value,AppelloEnum.A2.value);
        assertEquals(exam.getSubjectCourse(),course);
        assertEquals(SessionEnum.INVERNALE,exam.getSession());
    }

    @Test
    void TestGetExamById() throws DAOException, UserNotFoundException, PropertyException, WrongListQueryIdentifierValue, ObjectNotFoundException, ResourceNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException {
        ExamLazyFactory TestGetExamById = ExamLazyFactory.getInstance();
        UUID id = UUID.fromString("77dea2ad-3c8c-40c6-a278-7cf1a1ac9384");
        Exam exam = TestGetExamById.getExamById(id);
        assertNotNull(exam);
        assertEquals(exam.getId().toString(),id.toString());
    }
}
