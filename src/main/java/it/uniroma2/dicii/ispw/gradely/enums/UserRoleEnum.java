package it.uniroma2.dicii.ispw.gradely.enums;

import it.uniroma2.dicii.ispw.gradely.exceptions.UnrecognizedRoleException;

public enum UserRoleEnum {

    STUDENT(0),

    PROFESSOR(1),

    SECRETARY(2);

    public final int type;

    UserRoleEnum(int type) {
        this.type = type;
    }

    public static UserRoleEnum getUserRoleByType(int type) throws UnrecognizedRoleException {
        for (UserRoleEnum userRole : values())
            if (userRole.type == type)
                return userRole;
        throw new UnrecognizedRoleException(ExceptionMessagesEnum.UNRECOGNIZED_ROLE.message);
    }
}
