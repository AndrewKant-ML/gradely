package it.uniroma2.dicii.ispw.gradely.enums;

public enum DegreeCourseTypeEnum {
    TRIENNALE(1),
    MAGISTRALE(2),
    MASTER(3),
    DOTTORATO(4);

    public final int value;

    DegreeCourseTypeEnum(int value) {
        this.value = value;
    }

    public static DegreeCourseTypeEnum getDegreeCourseTypeByValue(int value) {
        for (DegreeCourseTypeEnum degreeCourseType : values())
            if (degreeCourseType.value == value)
                return degreeCourseType;
        return null;
    }
}
