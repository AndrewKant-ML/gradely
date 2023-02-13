package it.uniroma2.dicii.ispw.gradely.enums;

public enum RoomEnum {
    ROOM_A1(1),
    ROOM_A2(2),
    ROOM_A3(3),
    ROOM_B1(4),
    ROOM_B2(5),
    ROOM_B3(6);

    public final int value;

    RoomEnum(int value) {
        this.value = value;
    }

    public static RoomEnum getRoomByValue(int value){
        for(RoomEnum r : values())
            if (r.value == value)
                return r;
        return null;
    }
}
