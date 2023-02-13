package it.uniroma2.dicii.ispw.gradely.enums;

public enum TestTypeEnum {

    ONLINE(1),

    MUR(2);

    public final int value;

    TestTypeEnum(int value) {
        this.value = value;
    }

    public static TestTypeEnum getTestTypeByValue(int value) {
        for (TestTypeEnum t : values())
            if (t.value == value)
                return t;
        return null;
    }
}
