package it.uniroma2.dicii.ispw.gradely.model.test;

import it.uniroma2.dicii.ispw.gradely.CSVParser;
import it.uniroma2.dicii.ispw.gradely.dao_abstract.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourseLazyFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TestDAOFS implements TestDAOAbstract {

    protected final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    protected static TestDAOAbstract instance;
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
    public void saveTestInfo(Test test) {

    }

    @Override
    public Test getTestById(String id) throws PropertyException, ResourceNotFoundException, DAOException, ObjectNotFoundException {
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
        } catch ( IOException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

}
