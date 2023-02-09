package it.uniroma2.dicii.ispw.gradely.model.title;

import it.uniroma2.dicii.ispw.gradely.dao_abstract.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.DegreeCourseCodeEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourseLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;
import it.uniroma2.dicii.ispw.gradely.model.role.student.StudentLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.user.UserLazyFactory;

import java.sql.*;
import java.util.List;

public class TitleDAODB extends DAODBAbstract<Title> implements TitleDAOInterface {
    protected static TitleDAOInterface instance;

    private TitleDAODB() {
        super();
    }


    public static synchronized TitleDAOInterface getInstance() {
        if (instance == null) {
            instance = new TitleDAODB();
        }
        return instance;
    }

    @Override
    public List<Title> getTitlesByStudent(Student student, List<Title> oldTitles) throws DAOException, PropertyException, ResourceNotFoundException, UserNotFoundException, MissingAuthorizationException, UnrecognizedRoleException {
        return getListQuery("TITLE",List.of("student"),List.of(student.getUser().getCodiceFiscale()),oldTitles,List.of(student));
    }

    @Override
    public void insert(Title title) throws DAOException, PropertyException, ResourceNotFoundException {
        insertQuery("TITLE", List.of("student", "abstract_degree_course", "achievement_date"), title);
    }

    @Override
    public void cancel(Title title) throws DAOException, PropertyException, ResourceNotFoundException {
        cancelQuery("TITLE",List.of("codice_fiscale", "abstract_degree_course"), List.of(title.getStudent().getUser().getCodiceFiscale(), title.getDegreeCourse().getCode().value));
    }

    @Override
    public void update(Title title) throws DAOException, PropertyException, ResourceNotFoundException {
        cancelQuery("TITLE",List.of("codice_fiscale", "abstract_degree_course"), List.of(title.getStudent().getUser().getCodiceFiscale(), title.getDegreeCourse().getCode().value));

    }

    @Override
    protected void setInsertQueryParametersValue(PreparedStatement stmt, Title title) throws SQLException {
        setUpdateQueryParametersValue(stmt, title);
        stmt.setDate(3, Date.valueOf(title.getAchievementDate()));
    }

    @Override
    protected void setUpdateQueryParametersValue(PreparedStatement stmt, Title title) throws SQLException {
        stmt.setString(1,title.getStudent().getUser().getCodiceFiscale());
        stmt.setInt(2,title.getDegreeCourse().getCode().value);
    }

    @Override
    protected void setQueryIdentifiers(PreparedStatement stmt, List<Object> identifiersValues, String queryType) throws SQLException {
        stmt.setString(1, (String) identifiersValues.get(0));
        if(queryType.equals("cancel")){
            stmt.setInt(2, (int) identifiersValues.get(1));
        }
    }

    protected Title getListQueryObjectBuilder(ResultSet rs, List<Object> objects) throws SQLException, DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException, UserNotFoundException, UnrecognizedRoleException {
        return new Title(
                DegreeCourseLazyFactory.getInstance().getDegreeCourseByDegreeCourseCodeList(List.of(DegreeCourseCodeEnum.getDegreeCourseCodeByValue(rs.getInt("absract_degree_course")))).get(0),
                (Student) objects.get(0),
                rs.getDate("achievement_date").toLocalDate()
        );
    }

    @Override
    protected Title getQueryObjectBuilder(ResultSet rs, List<Object> objects) throws SQLException, DAOException, PropertyException, ResourceNotFoundException, UnrecognizedRoleException, UserNotFoundException {
        return new Title(
                DegreeCourseLazyFactory.getInstance().getDegreeCourseByDegreeCourseCodeList(List.of(DegreeCourseCodeEnum.getDegreeCourseCodeByValue(rs.getInt("absract_degree_course")))).get(0),
                StudentLazyFactory.getInstance().getStudentByUser(UserLazyFactory.getInstance().getUserByCodiceFiscale(rs.getString("codice_fiscale"))),
                rs.getDate("achievement_date").toLocalDate()
        );
    }

    @Override
    protected String getListQueryIdentifierValue(Title title, int valueNumber) throws DAOException {
        if(valueNumber == 0){
            return title.getStudent().getUser().getCodiceFiscale();
        } else throw new DAOException("wrong list query id value");
    }

}
