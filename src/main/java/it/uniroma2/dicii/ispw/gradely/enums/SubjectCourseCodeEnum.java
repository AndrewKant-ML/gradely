package it.uniroma2.dicii.ispw.gradely.enums;

public enum SubjectCourseCodeEnum {
    C01(1),
    C02(2),
    C03(3),
    C04(4),
    C05(5),
    C06(6),
    C07(7),
    C08(8),
    C09(9),
    C10(10);

    public final int value;

    SubjectCourseCodeEnum(int value) {
        this.value = value;
    }

    public static SubjectCourseCodeEnum getSubjectCourseCodeByValue(int value) {
        for (SubjectCourseCodeEnum subjectCourseCodeEnum : values())
            if (subjectCourseCodeEnum.value == value)
                return subjectCourseCodeEnum;
        return null;
    }
}
