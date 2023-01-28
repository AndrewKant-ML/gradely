package it.uniroma2.dicii.ispw.gradely.lazy_factories;

import it.uniroma2.dicii.ispw.gradely.daos.factories.DAOFactory;
import it.uniroma2.dicii.ispw.gradely.model.Professor;
import it.uniroma2.dicii.ispw.gradely.model.User;

import java.util.ArrayList;
import java.util.List;

public class ProfessorLazyFactory {
    private static ProfessorLazyFactory instance;
    private List<Professor> professors;

    private ProfessorLazyFactory(){
        professors = new ArrayList<Professor>();
    }

    public static ProfessorLazyFactory getInstance(){
        if (instance == null) {
            instance = new ProfessorLazyFactory();
        }
        return instance;
    }

    public Professor getProfessorByUser(User user) {
        for(Professor s : professors){
            if(s.getUser().equals(user)) {
                return s; //TODO implementare exception
            }
        }
        return DAOFactory.getDAOFactory().getProfessorDAO().getProfessorByUser(user); //TODO implementare exceptions
    }
}
