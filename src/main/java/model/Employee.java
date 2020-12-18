package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Employee {
    private String login;
    private String firstname;
    private String lastname;
    private String patronymic;
    private Position position;

    public String toString() {
        return String.format("Сотрудник: %s %s %s %s (%s)", login, lastname, firstname, patronymic, position.getName());
    }
}
