package it.uniroma2.dicii.ispw.gradely.daos.professor;

import it.uniroma2.dicii.ispw.gradely.lazy_factories.UserLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.Professor;
import it.uniroma2.dicii.ispw.gradely.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAOFS extends AbstractProfessorDAO {

    private ProfessorDAOFS(){ //TODO implementare costruttore vero
        professors = new ArrayList<Professor>();
        professors.add(new Professor(UserLazyFactory.getInstance().getUserByEmail("m.rossi@uniroma2.it"), "123456", LocalDate.now()));
    }

    public static AbstractProfessorDAO getInstance(){
        if (instance == null) {
            instance = new ProfessorDAOFS();
        }
        return instance;
    }

    @Override
    public Professor getProfessorByUser(User user) {
        for(Professor p : professors){
            if(p.getUser().equals(user)) {
                return p; //TODO implementare exceptions
            }
        }
        return null; //TODO implementare exceptions
    }

    @Override
    public void update(Professor professor) {

    }

    @Override
    public List<Professor> refresh(List<Professor> professors) {
        return null;
    }
}
