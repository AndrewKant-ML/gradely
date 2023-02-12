package it.uniroma2.dicii.ispw.gradely.model.title;

import com.opencsv.exceptions.CsvException;
import it.uniroma2.dicii.ispw.gradely.CSVParser;
import it.uniroma2.dicii.ispw.gradely.enums.DegreeCourseCodeEnum;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.instances_management_abstracts.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourseLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TitleDAOFS extends DAODBAbstract<Title> implements TitleDAOInterface {
    protected static TitleDAOInterface instance;

    private final String fileName = "title";

    private TitleDAOFS() {
        super();
    }


    public static synchronized TitleDAOInterface getInstance() {
        if (instance == null) {
            instance = new TitleDAOFS();
        }
        return instance;
    }

    @Override
    public List<Title> getTitlesByStudent(Student student, List<Title> list) throws ResourceNotFoundException, DAOException {
        try {
            List<Title> titles = new ArrayList<>();
            List<List<String>> lines = new CSVParser().readAllLines(fileName);
            for (List<String> line : lines) {
                if (line.get(1).equals(student.getCodiceFiscale()) &&
                        !checkPresenceByDegreeCourseAndStudent(line.get(0), line.get(1), list)) {
                    DegreeCourseCodeEnum code = DegreeCourseCodeEnum.getDegreeCourseCodeByValue(Integer.parseInt(line.get(0)));
                    titles.add(new Title(
                            DegreeCourseLazyFactory.getInstance().getDegreeCourseByDegreeCourseCodeList(List.of(code)).get(0),
                            student,
                            LocalDate.parse(line.get(2))
                    ));
                }
            }
            return titles;
        } catch (CsvException | WrongDegreeCourseCodeException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    private boolean checkPresenceByDegreeCourseAndStudent(String degreeCourseCode, String codiceFiscale, List<Title> titles) {
        for (Title title : titles)
            if (String.valueOf(title.getDegreeCourse().getCode().value).equals(degreeCourseCode)
                    && title.getStudent().getCodiceFiscale().equals(codiceFiscale))
                return true;
        return false;
    }

    @Override
    public void insert(Title title) throws DAOException {
        // To be implemented
    }

    @Override
    public void delete(Title title) throws DAOException {
        // To be implemented
    }

    @Override
    public void update(Title title) throws DAOException {
        // To be implemented
    }

    @Override
    protected Title queryObjectBuilder(ResultSet rs, List<Object> objects) throws SQLException, DAOException, PropertyException, ResourceNotFoundException, UnrecognizedRoleException, UserNotFoundException, MissingAuthorizationException, WrongDegreeCourseCodeException, ObjectNotFoundException {
        return null;
    }

    @Override
    protected String setGetListQueryIdentifiersValue(Title title, int valueNumber) throws DAOException, WrongListQueryIdentifierValue {
        return null;
    }

}
