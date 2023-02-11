package it.uniroma2.dicii.ispw.gradely.model.test;

import it.uniroma2.dicii.ispw.gradely.dao_abstract.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;

import java.time.format.DateTimeFormatter;

public interface TestDAOAbstract{


    abstract void saveTestInfo(Test test) throws PropertyException, ResourceNotFoundException, DAOException;

    abstract Test getTestById(String id) throws PropertyException, ResourceNotFoundException, DAOException, ObjectNotFoundException, WrongDegreeCourseCodeException;
}
