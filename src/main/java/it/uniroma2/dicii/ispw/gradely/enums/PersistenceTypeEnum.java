package it.uniroma2.dicii.ispw.gradely.enums;

public enum PersistenceTypeEnum {
    DB("DB"),
    FS("FS");

    public final String value;

    PersistenceTypeEnum(String value) {
        this.value = value;
    }
}
