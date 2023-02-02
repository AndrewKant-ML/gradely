package it.uniroma2.dicii.ispw.gradely.enums;

public enum UserRoleEnum {

    STUDENT(0),

    PROFESSOR(1),

    SECRETARY(2);

    public final int type;

    UserRoleEnum(int type) {
        this.type = type;
    }
}
