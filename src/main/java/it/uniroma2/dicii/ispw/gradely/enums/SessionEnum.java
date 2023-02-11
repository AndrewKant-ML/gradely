package it.uniroma2.dicii.ispw.gradely.enums;

public enum SessionEnum {
    INVERNALE(0),
    ESTIVA(1),
    AUTUNNALE(2);

    public final int value;

    SessionEnum(int value) {
        this.value = value;
    }

    public static SessionEnum getSessionByValue(int value) {
        for (SessionEnum s : values())
            if (s.value == value)
                return s;
        return null;
    }
}
