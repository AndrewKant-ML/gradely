package it.uniroma2.dicii.ispw.gradely.model.degree_course.dao;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DBConnection;
import it.uniroma2.dicii.ispw.gradely.enums.*;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DegreeCourseDAODB extends AbstractDegreeCourseDAO {

    private DegreeCourseDAODB(){ 

    }

    public static synchronized AbstractDegreeCourseDAO getInstance(){
        if (instance == null){
            instance = new DegreeCourseDAODB();
        }
        return instance;
    }

    @Override
    public DegreeCourse getDegreeCourseByName(String name) throws DAOException {
        return null;
    }

    @Override
    public List<DegreeCourse> getAllDegreeCourses(List<DegreeCourse> degreeCourses) throws DAOException {
        String query = "";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
                List<DegreeCourse> courses = new ArrayList<>();
                DegreeCourse degreeCourse;
                while (rs.next()) {
                    int code = rs.getInt("code");
                    degreeCourse = new DegreeCourse(
                            DegreeCourseCodeEnum.getDegreeCourseCodeByValue(code),
                            rs.getString("name"),
                            FacoltaEnum.valueOf(rs.getString("facolta")),
                            DipartimentoEnum.valueOf(rs.getString("dipartimento")),
                            DegreeCourseTypeEnum.valueOf(rs.getString("type")),
                            TestTypeEnum.valueOf(rs.getString("test_type"))
                    );
                    //degreeCourse.setPrerequisites(getDegreeCoursePrerequisitesByCode(code));
                }
                return courses;
            }
        } catch (IOException | SQLException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    public List<DegreeCourse> getDegreeCoursesByDegreeCourseCodeList(List<DegreeCourseCodeEnum> codes) throws DAOException {
        String query = "select DC.code as code,name,facolta,dipartimento,type,test_type from DEGREE_COURSE DC join ABSTRACT_DEGREE_COURSE ADC on DC.code = ADC.code where ADC.code IN (%s);";
        StringBuilder builder = new StringBuilder();
        for (DegreeCourseCodeEnum code : codes)
            builder.append(code.value + ',');
        builder.deleteCharAt(builder.length() - 1);
        query = String.format(query, builder);
        List<DegreeCourse> degreeCourses = new ArrayList<>();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    degreeCourses.add(new DegreeCourse(
                            DegreeCourseCodeEnum.getDegreeCourseCodeByValue(rs.getInt("code")),
                            rs.getString("name"),
                            FacoltaEnum.getFacoltaByValue(rs.getInt("facolta")),
                            DipartimentoEnum.getDipartimentoByValue(rs.getInt("dipartimento")),
                            DegreeCourseTypeEnum.valueOf(rs.getString("type")),
                            TestTypeEnum.getTestTypeByValue(rs.getInt("test_type"))
                    ));
                }
                return degreeCourses;
            } catch (SQLException e) {
                throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
            }
        } catch (IOException | SQLException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    @Override
    public void insert(DegreeCourse degreeCourse) {

    }

    @Override
    public void cancel(DegreeCourse degreeCourse) {

    }

    @Override
    public void update(DegreeCourse degreeCourse) {

    }

    @Override
    public List<DegreeCourse> refresh(List<DegreeCourse> degreeCourses) {
        return null;
    }
}
