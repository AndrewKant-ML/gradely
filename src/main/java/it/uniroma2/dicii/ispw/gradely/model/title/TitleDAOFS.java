package it.uniroma2.dicii.ispw.gradely.model.title;

import com.opencsv.exceptions.CsvException;
import it.uniroma2.dicii.ispw.gradely.CSVParser;
import it.uniroma2.dicii.ispw.gradely.enums.DegreeCourseCodeEnum;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ObjectNotFoundException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourseLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TitleDAOFS extends TitleDAOAbstract {

    private final String fileName = "title";

    private TitleDAOFS() {
        super();
    }


    public static synchronized TitleDAOAbstract getInstance() {
        if (instance == null) {
            instance = new TitleDAOFS();
        }
        return instance;
    }

    @Override
    List<Title> getTitlesByStudent(Student student, List<Title> list) throws PropertyException, ResourceNotFoundException, DAOException {
        try {
            List<Title> titles = new ArrayList<>();
            List<List<String>> lines = new CSVParser().readAllLines(fileName);
            for (List<String> line : lines) {
                if (line.get(1).equals(student.getUser().getCodiceFiscale()) &&
                        !checkPresenceByDegreeCourseAndStudent(line.get(0), line.get(1), list)) {
                    DegreeCourseCodeEnum code = DegreeCourseCodeEnum.getDegreeCourseCodeByValue(Integer.parseInt(line.get(0)));
                    if (code == null)
                        throw new ObjectNotFoundException(ExceptionMessagesEnum.OBJ_NOT_FOUND.message);
                    titles.add(new Title(
                            DegreeCourseLazyFactory.getInstance().getDegreeCourseByDegreeCourseCodeList(List.of(code)).get(0),
                            student,
                            LocalDate.parse(line.get(2))
                    ));
                }
            }
            return titles;
        } catch (CsvException | ObjectNotFoundException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    private boolean checkPresenceByDegreeCourseAndStudent(String degreeCourseCode, String codiceFiscale, List<Title> titles) {
        for (Title title : titles)
            if (String.valueOf(title.getDegreeCourse().getCode().value).equals(degreeCourseCode)
                    && title.getStudent().getUser().getCodiceFiscale().equals(codiceFiscale))
                return true;
        return false;
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

}
