package it.uniroma2.dicii.ispw.gradely.model.test;

import com.opencsv.exceptions.CsvException;
import it.uniroma2.dicii.ispw.gradely.CSVParser;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ObjectNotFoundException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourseLazyFactory;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;

public class TestDAOFS extends TestDAOAbstract {

    private final String fileName = "test";

    private TestDAOFS() {
        super();
    }

    public static synchronized TestDAOAbstract getInstance() {
        if (instance == null) {
            instance = new TestDAOFS();
        }
        return instance;
    }

    @Override
    void saveTestInfo(Test test) {

    }

    @Override
    Test getTestById(String id) throws PropertyException, ResourceNotFoundException, DAOException, ObjectNotFoundException {
        try {
            List<List<String>> lines = new CSVParser().readAllLines(fileName);
            for (List<String> line : lines)
                if (line.get(0).equals(id))
                    return new Test(
                            DegreeCourseLazyFactory.getInstance().getDegreeCourseByName(line.get(7)),
                            id,
                            LocalDate.parse(line.get(1)),
                            new URL(line.get(3)),
                            LocalDate.parse(line.get(2)),
                            new URL(line.get(4)),
                            line.get(5)
                    );
            throw new ObjectNotFoundException(ExceptionMessagesEnum.OBJ_NOT_FOUND.message);
        } catch (CsvException | IOException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    @Override
    public void insert(Test test) {

    }

    @Override
    public void cancel(Test test) {

    }

    @Override
    public void update(Test test){

    }
}
