package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Position {
    ENGINEER("инженер"),
    WORKER("рабочий");

    private final String name;
}
