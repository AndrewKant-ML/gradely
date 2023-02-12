package it.uniroma2.dicii.ispw.gradely.model.exam;

import it.uniroma2.dicii.ispw.gradely.enums.AppelloEnum;
import it.uniroma2.dicii.ispw.gradely.enums.RoomEnum;
import it.uniroma2.dicii.ispw.gradely.enums.SessionEnum;
import it.uniroma2.dicii.ispw.gradely.enums.SubjectCourseCodeEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.instances_management_abstracts.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment.ExamEnrollmentLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourseLazyFactory;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.UUID;

public class ExamDAODB extends DAODBAbstract<Exam> implements ExamDAOInterface {
    private static final String TABLE = "EXAM";
    private static final String SC_CODE = "sc_code";
    private static final String SC_NAME = "sc_name";
    private static final String SC_CFU = "sc_cfu";
    private static final String SC_AA = "sc_aa";
    private static final String EX_SESSION = "ex_session";
    private static final String APPELLO = "appello";

    protected static ExamDAOInterface instance;

    private ExamDAODB(){
    }

    public static synchronized ExamDAOInterface getInstance(){
        if (instance == null){
            instance = new ExamDAODB();
        }
        return instance;
    }

    @Override
    public List<Exam> getExamsBySubjectCourse(SubjectCourse subjectCourse, List<Exam> exclusions) throws UserNotFoundException, DAOException, PropertyException, WrongListQueryIdentifierValue, ObjectNotFoundException, ResourceNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException {
        return getListQuery(
                TABLE,
                List.of(SC_CODE,SC_NAME,SC_CFU,SC_AA),
                List.of(subjectCourse.getCode().value,subjectCourse.getName(),subjectCourse.getCfu(), Date.valueOf(subjectCourse.getAcademicYear().atDay(1))),
                exclusions,
                List.of(subjectCourse),
                false
        );
    }

    @Override
    public Exam getExamByAppelloAndSubjectCourseAndSession(AppelloEnum appello, SubjectCourse course, SessionEnum session) throws DAOException {
        return null;
    }

    @Override
    public Exam getExamById(UUID id) {
        return null;
    }

    @Override
    public void insert(Exam exam){
//TBI
    }

    @Override
    public void delete(Exam exam){
//TBI
    }

    @Override
    public void update(Exam exam){
        System.out.println("Updated");
    }

    @Override
    protected Exam queryObjectBuilder(ResultSet rs, List<Object> objects) throws SQLException, DAOException, PropertyException, ResourceNotFoundException, UnrecognizedRoleException, UserNotFoundException, MissingAuthorizationException, WrongDegreeCourseCodeException, ObjectNotFoundException, WrongListQueryIdentifierValue {
        LocalDate verbaleDate = rs.getDate("verbale_date") == null ? null : rs.getDate("verbale_date").toLocalDate();
        Exam exam = new Exam(
                UUID.fromString(rs.getString("id")),
                rs.getDate("enrollment_start_date").toLocalDate(),
                rs.getDate("enrollment_end_date").toLocalDate(),
                rs.getDate("examination_date").toLocalDate(),
                RoomEnum.getRoomByValue(rs.getInt("room")),
                AppelloEnum.getAppelloByValue(rs.getInt(APPELLO)),
                SessionEnum.getSessionByValue(rs.getInt(EX_SESSION)),
                (SubjectCourse) objects.get(0),
                rs.getBoolean("gradable"),
                rs.getBoolean("verbalizable"),
                verbaleDate,
                rs.getInt("verbale_number")
                );
        exam.setEnrollments(ExamEnrollmentLazyFactory.getInstance().getExamEnrollmentsByExam(exam));
        return exam;
    }

    @Override
    protected String setGetListQueryIdentifiersValue(Exam exam, int valueNumber) throws DAOException, WrongListQueryIdentifierValue {
        return null;
    }


}
