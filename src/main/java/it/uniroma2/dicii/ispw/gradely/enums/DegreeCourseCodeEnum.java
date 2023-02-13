package it.uniroma2.dicii.ispw.gradely.enums;

import it.uniroma2.dicii.ispw.gradely.exceptions.WrongDegreeCourseCodeException;

public enum DegreeCourseCodeEnum {
    L01(1),
    L02(2),
    L03(3),
    L04(4),
    L05(5),
    LM01(6),
    LM02(7),
    LM03(8),
    LM04(9),
    LM05(10);

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
