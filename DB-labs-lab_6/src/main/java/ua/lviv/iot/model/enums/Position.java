package ua.lviv.iot.model.enums;

import java.util.stream.Stream;

public enum Position {
    RESCUER, DOCTOR, DRIVER, FIREFIGHTER;

    public static Stream<Position> stream() {
        return Stream.of(Position.values());
    }
}
