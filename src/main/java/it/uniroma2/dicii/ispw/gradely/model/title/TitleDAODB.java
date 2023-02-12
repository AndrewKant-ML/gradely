package it.uniroma2.dicii.ispw.gradely.model.title;

import it.uniroma2.dicii.ispw.gradely.enums.DegreeCourseCodeEnum;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.instances_management_abstracts.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourseLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;
import it.uniroma2.dicii.ispw.gradely.model.role.student.StudentLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.user.UserLazyFactory;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TitleDAODB extends DAODBAbstract<Title> implements TitleDAOInterface {
    private static final String TITLE = "TITLE";
    private static final String CODICE_FISCALE ="codice_fiscale";
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
    public List<Title> getTitlesByStudent(Student student, List<Title> exclusions) throws DAOException, PropertyException, ResourceNotFoundException, UserNotFoundException, MissingAuthorizationException, UnrecognizedRoleException, WrongListQueryIdentifierValue, WrongDegreeCourseCodeException, ObjectNotFoundException {
        return getListQuery(
                TITLE,
                List.of("student"),
                List.of(student.getCodiceFiscale()),
                exclusions,
                List.of(student),
                false
        );
    }

    @Override
    public void insert(Title title) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException {
        insertQuery(
                TITLE,
                List.of(title.getStudent().getCodiceFiscale(), title.getDegreeCourse().getCode().value, Date.valueOf(title.getAchievementDate()))
        );
    }

    @Override
    public void delete(Title title) throws DAOException, PropertyException, ResourceNotFoundException {
        deleteQuery(TITLE,
                List.of(CODICE_FISCALE, "abstract_degree_course"),
                List.of(title.getStudent().getCodiceFiscale(), title.getDegreeCourse().getCode().value)
        );
    }

    @Override
    public void update(Title title) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException {
        updateQuery(
                TITLE,
                List.of("achievement_date"),
                List.of(Date.valueOf(title.getAchievementDate())),
                List.of(CODICE_FISCALE, "abstract_degree_course"),
                List.of(title.getStudent().getCodiceFiscale(),title.getDegreeCourse().getCode().value)
        );
    }

    @Override
    protected Title queryObjectBuilder(ResultSet rs, List<Object> objects) throws SQLException, DAOException, PropertyException, ResourceNotFoundException, UnrecognizedRoleException, UserNotFoundException, WrongDegreeCourseCodeException, ObjectNotFoundException, MissingAuthorizationException, WrongListQueryIdentifierValue {
        return new Title(
                DegreeCourseLazyFactory.getInstance().getDegreeCourseByDegreeCourseCodeList(List.of(DegreeCourseCodeEnum.getDegreeCourseCodeByValue(rs.getInt("absract_degree_course")))).get(0),
                StudentLazyFactory.getInstance().getStudentByUser(UserLazyFactory.getInstance().getUserByCodiceFiscale(rs.getString(CODICE_FISCALE))),
                rs.getDate("achievement_date").toLocalDate()
        );
    }

    @Override
    protected String setGetListQueryIdentifiersValue(Title title, int valueNumber) throws WrongListQueryIdentifierValue {
        if(valueNumber == 0){
            return title.getStudent().getCodiceFiscale();
        } else throw new WrongListQueryIdentifierValue(ExceptionMessagesEnum.WRONG_LIST_QUERY_IDENTIFIER_VALUE.message);
    }

}
