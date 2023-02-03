package it.uniroma2.dicii.ispw.gradely.enums;

public enum FrontEndTypeEnum {
    JAVAFX("JAVAFX"),
    CLI("CLI");

    public final String value;

    private FrontEndTypeEnum(String value) {
        this.value = value;
    }
}
