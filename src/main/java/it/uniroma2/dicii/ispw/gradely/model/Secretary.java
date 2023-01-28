package it.uniroma2.dicii.ispw.gradely.model;

import it.uniroma2.dicii.ispw.gradely.enums.DipartimentoEnum;

public class Secretary extends AbstractRole {
    private DipartimentoEnum dipartimento;
    public Secretary(User user, DipartimentoEnum dipartimento) {
        setUser(user);
        this.dipartimento = dipartimento;
    }

    public DipartimentoEnum getDipartimento() {
        return dipartimento;
    }

    public void setDipartimento(DipartimentoEnum dipartimento) {
        this.dipartimento = dipartimento;
    }

    @Override
    public Secretary secretary() {
        return this;
    }


}
