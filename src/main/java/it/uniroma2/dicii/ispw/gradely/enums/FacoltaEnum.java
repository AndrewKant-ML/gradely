package it.uniroma2.dicii.ispw.gradely.enums;

public enum FacoltaEnum {
    INGEGNERIA(0);

    public final int value;

    FacoltaEnum(int value) {
        this.value = value;
    }

    public static FacoltaEnum getFacoltaByValue(int value) {
        for (FacoltaEnum f : values())
            if (f.value == value)
                return f;
        return null;
    }
}
