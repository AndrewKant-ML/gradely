package it.uniroma2.dicii.ispw.gradely.model.title;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DBConnection;
import it.uniroma2.dicii.ispw.gradely.enums.DegreeCourseCodeEnum;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.MissingAuthorizationException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourseLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class TitleDAODB extends TitleDAOAbstract {

    private TitleDAODB() {
        super();
    }


    public static synchronized TitleDAOAbstract getInstance() {
        if (instance == null) {
            instance = new TitleDAODB();
        }
        return instance;
    }

    /**
     * Retrieves all Title of a given Student
     *
     * @param student   the Student whose titles have to be found
     * @param oldTitles the titles already loaded in memory
     * @return a List of Title objects
     * @throws DAOException              thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException         thrown if errors occur while loading db connection properties
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    @Override
    List<Title> getTitlesByStudent(Student student, List<Title> oldTitles) throws DAOException, PropertyException, ResourceNotFoundException {
        String query;
        if (oldTitles.isEmpty())
            query = String.format("select * from TITLE T where T.student='%s';", student.getUser().getCodiceFiscale());
        else {
            StringBuilder builder = new StringBuilder();
            for (Title title : oldTitles)
                builder.append(title.getDegreeCourse().getCode().value).append(',');
            builder.deleteCharAt(builder.length() - 1);
            query = String.format("select * from TITLE T where T.student='%s' and T.abstract_degree_course not in (%s);", student.getUser().getCodiceFiscale(), builder);
        }
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
                List<Title> newTitles = new ArrayList<>();
                List<DegreeCourseCodeEnum> coursesCodes;
                while (rs.next()) {
                    coursesCodes = new ArrayList<>();
                    coursesCodes.add(DegreeCourseCodeEnum.getDegreeCourseCodeByValue(rs.getInt("abstract_degree_course")));
                    newTitles.add(new Title(
                            DegreeCourseLazyFactory.getInstance().getDegreeCourseByDegreeCourseCodeList(coursesCodes).get(0),
                            student,
                            rs.getDate("achievement_date").toLocalDate()
                    ));
                }
                return newTitles;
            }
        } catch (SQLException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    @Override
    public void insert(Title title) throws DAOException, PropertyException, ResourceNotFoundException {
        String query = "insert into TITLE (student, abstract_degree_course, achievement_date) values (?, ?, ?)";
        setQueryParameters(title, query);
    }

    @Override
    public void cancel(Title title) throws DAOException, PropertyException, ResourceNotFoundException {
        String query = "delete from TITLE where student = ? and abstract_degree_course = ?";
        try{
            Connection connection = DBConnection.getInstance().getConnection();
            try(PreparedStatement stmt = connection.prepareStatement(query)){
                stmt.setString(1,title.getStudent().getUser().getCodiceFiscale());
                stmt.setInt(2,title.getDegreeCourse().getCode().value);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message,e);
        }

    }

    @Override
    public void update(Title title) throws DAOException, PropertyException, ResourceNotFoundException {
        String query = "update TITLE set student = ?, abstract_degree_course = ?, achievement_date = ? where student = ? and abstract_degree_course = ?";
        setQueryParameters(title, query);
    }

    private void setQueryParameters(Title title, String query) throws ResourceNotFoundException, PropertyException, DAOException {
        try{
            Connection connection = DBConnection.getInstance().getConnection();
            try(PreparedStatement stmt = connection.prepareStatement(query)){
                stmt.setString(1,title.getStudent().getUser().getCodiceFiscale());
                stmt.setInt(2,title.getDegreeCourse().getCode().value);
                stmt.setDate(3, Date.valueOf(title.getAchievementDate()));
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message,e);
        }
    }

}
