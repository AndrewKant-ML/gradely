package it.uniroma2.dicii.ispw.gradely.model.role.professor;

import java.util.ArrayList;
import java.util.List;

public class ProfessorLazyFactory {
    private static ProfessorLazyFactory instance;
    private List<Professor> professors;

    private ProfessorLazyFactory(){
        professors = new ArrayList<Professor>();
    }

    public static synchronized ProfessorLazyFactory getInstance(){
        if (instance == null){
            instance = new ProfessorLazyFactory();
        }
        return instance;
    }

}
