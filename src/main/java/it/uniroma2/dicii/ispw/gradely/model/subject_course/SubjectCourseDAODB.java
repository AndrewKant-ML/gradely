package it.uniroma2.dicii.ispw.gradely.model.subject_course;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DBConnection;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.enums.SubjectCourseCodeEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ObjectNotFoundException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.List;


public class SubjectCourseDAODB extends SubjectCourseDAOAbstract {

    public SubjectCourseDAODB() {
        super();
    }

    public static synchronized SubjectCourseDAOAbstract getInstance() {
        if (instance == null) {
            instance = new SubjectCourseDAODB();
        }
        return instance;
    }

    private SubjectCourse querySingleSubjectCourseData(String query) throws ObjectNotFoundException, DAOException, PropertyException, ResourceNotFoundException {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
                if (rs.first()) {
                    return new SubjectCourse(
                            SubjectCourseCodeEnum.getSubjectCourseCodeByValue(rs.getInt("code")),
                            rs.getString("name"),
                            Year.of(rs.getDate("year").toLocalDate().getYear()),
                            rs.getInt("cfu")
                    );
                } else
                    throw new ObjectNotFoundException(ExceptionMessagesEnum.OBJ_NOT_FOUND.message);
            }
        } catch (SQLException | IOException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    @Override
    public SubjectCourse getSubjectCourseByNameCodeCfuAndAcademicYear(String name, SubjectCourseCodeEnum code, Integer cfu, Year academicYear) throws ObjectNotFoundException, DAOException, PropertyException, ResourceNotFoundException {
        String query = "select * from SUBJECT_COURSE SC where SC.code=%d and SC.name=%s and SC.cfu=%d and SC.ss=%d;";
        query = String.format(query, code.value, name, cfu, academicYear.getValue());
        return querySingleSubjectCourseData(query);
    }

    @Override
    public void insert(SubjectCourse subjectCourse) {

    }

    @Override
    public void cancel(SubjectCourse subjectCourse) {

    }

    @Override
    public void update(SubjectCourse subjectCourse) {

    }

    @Override
    public List<SubjectCourse> refresh(List<SubjectCourse> subjectCourses) {
        return null;
    }


}
