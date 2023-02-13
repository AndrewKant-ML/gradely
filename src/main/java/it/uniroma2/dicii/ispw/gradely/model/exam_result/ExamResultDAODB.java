package it.uniroma2.dicii.ispw.gradely.model.exam_result;

import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.instances_management_abstracts.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.model.exam.Exam;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExamResultDAODB extends DAODBAbstract<ExamResult> implements ExamResultDAOInterface {
    protected static ExamResultDAOInterface instance;

    private static final String TABLE_NAME = "EXAM_RESULT";
    private static final String EXAM_SC_CODE = "exam_sc_code";
    private static final String EXAM_SC_NAME = "exam_sc_name";
    private static final String EXAM_SC_CFU = "exam_sc_cfu";
    private static final String EXAM_SC_AA = "exam_sc_aa";
    private static final String EXAM_SESSION = "exam_session";
    private static final String EXAM_APPELLO = "exam_appello";
    private static final String STUDENT = "student";

    private ExamResultDAODB() {

    }

    public static synchronized ExamResultDAOInterface getInstance() {
        if (instance == null) {
            instance = new ExamResultDAODB();
        }
        return instance;
    }


    @Override
    public ExamResult getExamResultByStudentAndExam(Student student, Exam exam) throws DAOException, PropertyException, ResourceNotFoundException, UnrecognizedRoleException, ObjectNotFoundException, MissingAuthorizationException, WrongDegreeCourseCodeException, WrongListQueryIdentifierValue {
        try {
            return getQuery(
                    TABLE_NAME,
                    List.of(EXAM_SC_CODE, EXAM_SC_NAME, EXAM_SC_CFU, EXAM_SC_AA, EXAM_SESSION, EXAM_APPELLO, STUDENT),
                    List.of(exam.getSubjectCourse().getCode().value, exam.getSubjectCourse().getName(), exam.getSubjectCourse().getCfu(), Date.valueOf(exam.getSubjectCourse().getAcademicYear().atDay(1)), exam.getSession().value, exam.getAppello().value, student),
                    List.of(exam, student)
            );
        } catch (UserNotFoundException e) {
            return null; // TBI manage UserNotFoundException
        }
    }

    @Override
    public void insert(ExamResult examResult) throws DAOException, PropertyException, ResourceNotFoundException {
        Boolean confirmation;
        switch (examResult.getConfirmed()) {
            case REJECTED -> confirmation = false;
            case ACCEPTED -> confirmation = true;
            default -> confirmation = null;
        }
        List<Object> parametersValue = new ArrayList<>();
        parametersValue.add(examResult.getEnrollment().getExam().getSubjectCourse().getCode().value);
        parametersValue.add(examResult.getEnrollment().getExam().getSubjectCourse().getName());
        parametersValue.add(examResult.getEnrollment().getExam().getSubjectCourse().getCfu());
        parametersValue.add(Date.valueOf(examResult.getEnrollment().getExam().getSubjectCourse().getAcademicYear().atDay(1)));
        parametersValue.add(examResult.getEnrollment().getExam().getSession().value);
        parametersValue.add(examResult.getEnrollment().getExam().getAppello().value);
        parametersValue.add(examResult.getResult().value);
        parametersValue.add(examResult.getGrade());
        parametersValue.add(confirmation);
        parametersValue.add(examResult.getEnrollment().getStudent().getUser().getCodiceFiscale());
        insertQuery(
                TABLE_NAME, parametersValue
        );
    }

    @Override
    public void delete(ExamResult examResult) throws DAOException {
//TBI
    }

    @Override
    public void update(ExamResult examResult) throws DAOException {
//TBI
    }

    @Override
    protected ExamResult queryObjectBuilder(ResultSet rs, List<Object> objects) throws SQLException, DAOException, PropertyException, ResourceNotFoundException, UnrecognizedRoleException, UserNotFoundException, MissingAuthorizationException, WrongDegreeCourseCodeException, ObjectNotFoundException {
        return null;
    }

    @Override
    protected String setGetListQueryIdentifiersValue(ExamResult examResult, int valueNumber) throws DAOException {
        return null;
    }



}
