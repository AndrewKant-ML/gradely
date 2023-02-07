package it.uniroma2.dicii.ispw.gradely.beans_general;

public class SecretaryBean {
    private DipartimentoEnumBean dipartimento;

    public SecretaryBean(DipartimentoEnumBean dipartimento) {
        this.dipartimento = dipartimento;
    }

    public DipartimentoEnumBean getDipartimento() {
        return dipartimento;
    }

    public void setDipartimento(DipartimentoEnumBean dipartimento) {
        this.dipartimento = dipartimento;
    }
}
