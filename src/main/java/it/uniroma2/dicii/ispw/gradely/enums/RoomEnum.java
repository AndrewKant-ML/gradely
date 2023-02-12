package it.uniroma2.dicii.ispw.gradely.enums;

public enum RoomEnum {
    ROOM_A1(0),
    ROOM_A2(1),
    ROOM_A3(2),
    ROOM_B1(3),
    ROOM_B2(4),
    ROOM_B3(5);

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
