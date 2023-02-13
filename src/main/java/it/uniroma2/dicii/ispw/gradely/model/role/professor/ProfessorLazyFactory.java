package it.uniroma2.dicii.ispw.gradely.model.role.professor;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DAOFactoryAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.util.ArrayList;
import java.util.List;

public class ProfessorLazyFactory {
    private static ProfessorLazyFactory instance;
    private final List<Professor> factoryObjects;

    private ProfessorLazyFactory(){
        factoryObjects = new ArrayList<Professor>();
    }

    public static synchronized ProfessorLazyFactory getInstance() {
        if (instance == null) {
            instance = new ProfessorLazyFactory();
        }
        return instance;
    }

    public Professor getProfessorByUser(User user) throws DAOException, UserNotFoundException, ObjectNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException, WrongListQueryIdentifierValue {
        for (Professor p : this.factoryObjects)
            if (p.getUser().equals(user))
                return p;
        try {
            Professor professor = DAOFactoryAbstract.getInstance().getProfessorDAO().getProfessorByUser(user);
            factoryObjects.add(professor);
            return professor;
        } catch (PropertyException | ResourceNotFoundException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message);
        }
    }
}
