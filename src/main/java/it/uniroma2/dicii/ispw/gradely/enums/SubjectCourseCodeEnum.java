package it.uniroma2.dicii.ispw.gradely.enums;

public enum SubjectCourseCodeEnum {
    C01(10),
    C02(1),
    C03(2),
    C04(3),
    C05(4),
    C06(5),
    C07(6),
    C08(7),
    C09(8),
    C10(9);

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
