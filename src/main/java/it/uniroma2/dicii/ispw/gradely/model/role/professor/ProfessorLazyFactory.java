package it.uniroma2.dicii.ispw.gradely.model.role.professor;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DAOFactoryAbstract;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
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

    public Professor getProfessorByUser(User user) throws DAOException, UserNotFoundException, ObjectNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException, WrongListQueryIdentifierValue, PropertyException, ResourceNotFoundException {
        for (Professor p : this.professors)
            if (p.getUser().equals(user))
                return p;
        return DAOFactoryAbstract.getInstance().getProfessorDAO().getProfessorByUser(user);
    }
}
