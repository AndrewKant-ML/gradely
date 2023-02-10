package it.uniroma2.dicii.ispw.gradely.enums;

import it.uniroma2.dicii.ispw.gradely.exceptions.WrongDegreeCourseCodeException;

public enum DegreeCourseCodeEnum {
    L01(0),
    L02(1),
    L03(2),
    L04(3),
    L05(4),
    LM01(5),
    LM02(6),
    LM03(7),
    LM04(8),
    LM05(9);

    public final int value;

    DegreeCourseCodeEnum(int value) {
        this.value = value;
    }

    public static DegreeCourseCodeEnum getDegreeCourseCodeByValue(int value) throws WrongDegreeCourseCodeException {
        for (DegreeCourseCodeEnum code: values())
            if (code.value == value)
                return code;
        throw new WrongDegreeCourseCodeException(ExceptionMessagesEnum.WRONG_DEGREE_COURSE_CODE.message);
    }
}
