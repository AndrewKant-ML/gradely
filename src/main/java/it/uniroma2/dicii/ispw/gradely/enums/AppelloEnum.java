package it.uniroma2.dicii.ispw.gradely.enums;

public enum AppelloEnum {
    A1(1),
    A2(2),
    A3(3),
    A4(4),
    A5(5),
    A6(6)
    ;

    public Integer value;

    AppelloEnum(Integer value) {
        this.value = value;
    }

    public static AppelloEnum getAppelloByValue(int value) {
        for (AppelloEnum a : values())
            if (a.value == value)
                return a;
        return null;
    }
}
