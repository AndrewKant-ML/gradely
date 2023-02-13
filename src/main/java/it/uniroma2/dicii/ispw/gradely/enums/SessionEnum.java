package it.uniroma2.dicii.ispw.gradely.enums;

public enum SessionEnum {
    INVERNALE(1),
    ESTIVA(2),
    AUTUNNALE(3);

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
