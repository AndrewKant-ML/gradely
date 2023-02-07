package it.uniroma2.dicii.ispw.gradely.beans_general;

import it.uniroma2.dicii.ispw.gradely.enums.DipartimentoEnum;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.BeanFormatException;

public class DipartimentoEnumBean {
    private String dipartimento;
    private Integer value;

    public DipartimentoEnumBean(DipartimentoEnum dipartimento) throws BeanFormatException {
        switch (dipartimento){
            case DICII -> this.dipartimento="DICII";
            case DIE -> this.dipartimento="DIE";
            case DII -> this.dipartimento="DII";
            case DIIND -> this.dipartimento="DIIND";
            default -> throw new BeanFormatException(ExceptionMessagesEnum.BEAN_FORMAT.message);
        }
        this.value = dipartimento.value;
    }

    public String getDipartimento() {
        return dipartimento;
    }

    public void setDipartimento(String dipartimento) {
        this.dipartimento = dipartimento;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
