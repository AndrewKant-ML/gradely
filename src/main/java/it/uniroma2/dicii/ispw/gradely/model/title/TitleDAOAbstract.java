package it.uniroma2.dicii.ispw.gradely.model.title;

import it.uniroma2.dicii.ispw.gradely.dao_abstract.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;

import java.util.List;

public abstract class TitleDAOAbstract implements DAODBAbstract<Title> {
    protected static TitleDAOAbstract instance;

    protected TitleDAOAbstract(){
    }

    abstract List<Title> getTitlesByStudent(Student student, List<Title> list) throws PropertyException, ResourceNotFoundException, DAOException;
}
