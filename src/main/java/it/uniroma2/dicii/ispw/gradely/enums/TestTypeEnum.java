package it.uniroma2.dicii.ispw.gradely.enums;

public enum TestTypeEnum {

    ONLINE(0),

    MUR(1);

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
