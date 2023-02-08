package it.uniroma2.dicii.ispw.gradely.model.test;

import it.uniroma2.dicii.ispw.gradely.dao_abstract.DAOAbstract;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ObjectNotFoundException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;

import java.time.format.DateTimeFormatter;

public abstract class TestDAOAbstract implements DAOAbstract<Test> {

    protected final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    protected static TestDAOAbstract instance;

    protected TestDAOAbstract(){
    }

    abstract void saveTestInfo(Test test) throws PropertyException, ResourceNotFoundException, DAOException;

    abstract Test getTestById(String id) throws PropertyException, ResourceNotFoundException, DAOException, ObjectNotFoundException;
}
