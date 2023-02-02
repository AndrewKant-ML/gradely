package it.uniroma2.dicii.ispw.gradely.model.role.secretary;

import it.uniroma2.dicii.ispw.gradely.enums.DipartimentoEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.MissingAuthorizationException;
import it.uniroma2.dicii.ispw.gradely.model.role.AbstractRole;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

public class Secretary extends AbstractRole {
    private DipartimentoEnum dipartimento;
    public Secretary(User user, DipartimentoEnum dipartimento){
        super(user);
        this.dipartimento = dipartimento;
    }

    public DipartimentoEnum getDipartimento(){
        return dipartimento;
    }

    public void setDipartimento(DipartimentoEnum dipartimento){
        this.dipartimento = dipartimento;
    }

    @Override
    public Secretary getSecretaryRole() throws MissingAuthorizationException {
        return this;
    }


}
