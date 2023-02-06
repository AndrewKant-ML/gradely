package it.uniroma2.dicii.ispw.gradely.model.role.professor;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DAOFactoryDB;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;
import it.uniroma2.dicii.ispw.gradely.exceptions.UserNotFoundException;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.util.ArrayList;
import java.util.List;

public class ProfessorLazyFactory {
    private static ProfessorLazyFactory instance;
    private final List<Professor> professors;

    private ProfessorLazyFactory(){
        professors = new ArrayList<Professor>();
    }

    public static synchronized ProfessorLazyFactory getInstance() {
        if (instance == null) {
            instance = new ProfessorLazyFactory();
        }
        return instance;
    }

    public Professor getProfessorByUser(User user) throws DAOException, UserNotFoundException {
        for (Professor p : this.professors)
            if (p.getUser().equals(user))
                return p;
        try {
            return DAOFactoryDB.getInstance().getProfessorDAO().getProfessorByUser(user);
        } catch (PropertyException | ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
