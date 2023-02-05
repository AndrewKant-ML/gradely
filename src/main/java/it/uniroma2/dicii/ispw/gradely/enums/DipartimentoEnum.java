package it.uniroma2.dicii.ispw.gradely.enums;

public enum DipartimentoEnum {
    DICII(0),
    DIE(1),
    DII(2),
    DIIND(3);

    public final int value;

    DipartimentoEnum(int value) {
        this.value = value;
    }

    public static DipartimentoEnum getDipartimentoByValue(int value) {
        for (DipartimentoEnum d : values())
            if (d.value == value)
                return d;
        return null;
    }
}
