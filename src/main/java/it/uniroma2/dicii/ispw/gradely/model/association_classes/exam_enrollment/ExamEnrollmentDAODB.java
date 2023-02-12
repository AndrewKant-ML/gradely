package it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment;

import it.uniroma2.dicii.ispw.gradely.enums.AppelloEnum;
import it.uniroma2.dicii.ispw.gradely.enums.SessionEnum;
import it.uniroma2.dicii.ispw.gradely.enums.SubjectCourseCodeEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.instances_management_abstracts.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.model.exam.Exam;
import it.uniroma2.dicii.ispw.gradely.model.exam.ExamLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;
import it.uniroma2.dicii.ispw.gradely.model.role.student.StudentLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourseLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.user.UserLazyFactory;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.List;

public class ExamEnrollmentDAODB extends DAODBAbstract<ExamEnrollment> implements ExamEnrollmentDAOInterface {
    private static final String EXAM_ENROLLMENT ="EXAM_ENROLLMENT";
    private static final String EXAM_SESSION = "exam_session";
    private static final String EXAM_APPELLO = "exam_appello";
    private static final String EXAM_SC_CODE = "exam_sc_code";
    private static final String EXAM_SC_NAME = "exam_sc_name";
    private static final String EXAM_SC_CFU = "exam_sc_cfu";
    private static final String EXAM_SC_AA = "exam_sc_aa";
    private static final String STUDENT = "student";
    private static final String ENROLLMENT_DATE = "enrollment_date";

    protected static ExamEnrollmentDAOInterface instance;

    private ExamEnrollmentDAODB(){

    }

    public static synchronized ExamEnrollmentDAOInterface getInstance(){
        if (instance == null){
            instance = new ExamEnrollmentDAODB();
        }
        return instance;
    }

    @Override
    public List<ExamEnrollment> getExamEnrollmentsByExam(Exam exam, List<ExamEnrollment> exclusions) throws UserNotFoundException, DAOException, PropertyException, WrongListQueryIdentifierValue, ObjectNotFoundException, ResourceNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException {
        return getListQuery(
                EXAM_ENROLLMENT,
                List.of(EXAM_SESSION, EXAM_APPELLO, EXAM_SC_CODE, EXAM_SC_NAME, EXAM_SC_CFU, EXAM_SC_AA),
                List.of(exam.getSession().value, exam.getAppello().value, exam.getSubjectCourse().getCode().value, exam.getSubjectCourse().getName(), exam.getSubjectCourse().getCfu(), Date.valueOf(exam.getSubjectCourse().getAcademicYear().atDay(1))),
                exclusions,
                List.of(exam),
                false
        );
    }

    @Override
    public List<ExamEnrollment> getExamEnrollmentsByStudent(Student student, List<ExamEnrollment> exclusions) throws UserNotFoundException, DAOException, PropertyException, WrongListQueryIdentifierValue, ObjectNotFoundException, ResourceNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException {
        return getListQuery(
                EXAM_ENROLLMENT,
                List.of(STUDENT),
                List.of(student.getCodiceFiscale()),
                exclusions,
                List.of(student),
                false
        );
    }

    @Override
    public ExamEnrollment getExamEnrollmentByExamAndStudent(Exam exam, Student student) throws UserNotFoundException, DAOException, PropertyException, WrongListQueryIdentifierValue, ObjectNotFoundException, ResourceNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException {
        return getQuery(
                EXAM_ENROLLMENT,
                List.of(STUDENT,EXAM_SESSION, EXAM_APPELLO, EXAM_SC_CODE, EXAM_SC_NAME, EXAM_SC_CFU, EXAM_SC_AA),
                List.of(exam.getSession().value,exam.getAppello().value,exam.getSubjectCourse().getCode().value,exam.getSubjectCourse().getName(),exam.getSubjectCourse().getCfu(), Date.valueOf(exam.getSubjectCourse().getAcademicYear().atDay(1))),
                null
        );
    }

    @Override
    public void insert(ExamEnrollment examEnrollment){
        //TBI
    }

    @Override
    public void delete(ExamEnrollment examEnrollment){
        //TBI
    }

    @Override
    public void update(ExamEnrollment examEnrollment){
        //TBI
    }

    @Override
    protected ExamEnrollment queryObjectBuilder(ResultSet rs, List<Object> objects) throws SQLException, DAOException, PropertyException, ResourceNotFoundException, UnrecognizedRoleException, UserNotFoundException, MissingAuthorizationException, WrongDegreeCourseCodeException, ObjectNotFoundException, WrongListQueryIdentifierValue {
        if (objects.get(0) instanceof Student)
            return new ExamEnrollment(
                    rs.getDate(ENROLLMENT_DATE).toLocalDate(),
                    (Student) objects.get(0),
                    ExamLazyFactory.getInstance().getExamByAppelloCourseAndSession(
                            AppelloEnum.getAppelloByValue(rs.getInt(EXAM_APPELLO)),
                            SubjectCourseLazyFactory.getInstance().getSubjectCourseByCodeNameCfuAndAcademicYear(
                                    SubjectCourseCodeEnum.getSubjectCourseCodeByValue(rs.getInt(EXAM_SC_CODE)),
                                    rs.getString(EXAM_SC_NAME),
                                    rs.getInt(EXAM_SC_CFU),
                                    Year.of(rs.getDate(EXAM_SC_AA).toLocalDate().getYear())
                            ),
                            SessionEnum.getSessionByValue(rs.getInt(EXAM_SESSION))
                    )
            );
        else
            return new ExamEnrollment(
                    rs.getDate(ENROLLMENT_DATE).toLocalDate(),
                    StudentLazyFactory.getInstance().getStudentByUser(UserLazyFactory.getInstance().getUserByCodiceFiscale(rs.getString(STUDENT))),
                    (Exam) objects.get(0)
            );
    }

    @Override
    protected String setGetListQueryIdentifiersValue(ExamEnrollment examEnrollment, int valueNumber) throws DAOException, WrongListQueryIdentifierValue {
        return null;
    }
}