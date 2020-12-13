package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@AllArgsConstructor

public enum Position {
    ENGINEER("инженер"),
    WORKER("рабочий");

    private String name;
}
