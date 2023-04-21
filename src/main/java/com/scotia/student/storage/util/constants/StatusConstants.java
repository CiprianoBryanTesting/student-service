package com.scotia.student.storage.util.constants;

import lombok.*;

import java.util.*;

@Getter
@AllArgsConstructor
public enum StatusConstants {
    ACTIVE("activo", true),
    INACTIVE("inactivo", false);

    public static StatusConstants findByDescription(String description) {
        return Arrays.stream(values()).filter(status -> status.getDescription().equals(description)).findFirst().orElse(null);
    }

    public static StatusConstants findByValue(boolean value) {
        return Arrays.stream(values()).filter(status -> status.getValue() == value).findFirst().orElse(null);
    }

    private final String description;
    private final Boolean value;
}
