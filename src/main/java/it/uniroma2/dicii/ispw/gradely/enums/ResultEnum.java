package it.uniroma2.dicii.ispw.gradely.enums;

public enum ResultEnum {
    PROMOSSO(1),
    RESPINTO(2),
    RITIRATO(3),
    ASSENTE(4);

    public final int value;

    ResultEnum(int value) {
        this.value = value;
    }

    public static ResultEnum getResultByValue(int value){
        for(ResultEnum r : values())
            if (r.value == value)
                return r;
        return null;
    }
}
