package it.uniroma2.dicii.ispw.gradely.model.title;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DBConnection;
import it.uniroma2.dicii.ispw.gradely.enums.DegreeCourseCodeEnum;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourseLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
     * @param titleList the titles already loaded in memory
     * @return a List of Title objects
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     */
    @Override
    List<Title> getTitlesByStudent(Student student, List<Title> titleList) throws DAOException {
        String query;
        if (titleList.size() == 0)
            query = String.format("select * from TITLE T where T.student='%s';", student.getUser().getCodiceFiscale());
        else {
            StringBuilder builder = new StringBuilder();
            for (Title title : titleList)
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
        } catch (SQLException | IOException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    @Override
    public void insert(Title title) throws DAOException {

    }

    @Override
    public void cancel(Title title) throws DAOException {

    }

    @Override
    public void update(Title title) throws DAOException {

    }

    @Override
    public List<Title> refresh(List<Title> titles) throws DAOException {
        return null;
    }
}
