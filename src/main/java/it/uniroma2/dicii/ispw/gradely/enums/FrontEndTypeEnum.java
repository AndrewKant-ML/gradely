package it.uniroma2.dicii.ispw.gradely.enums;

public enum FrontEndTypeEnum {
    JAVAFX("JAVAFX"),
    CLI("CLI");

    public final String value;

    FrontEndTypeEnum(String value) {
        this.value = value;
    }

    public static FrontEndTypeEnum getFrontEndTypeByValue(String value) {
        for (FrontEndTypeEnum frontEndType : values())
            if (frontEndType.value.equals(value))
                return frontEndType;
        return null;
    }
}
