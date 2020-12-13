package model;

import lombok.Data;

@Data
public class Employee {
    String login;
    String firstname;
    String lastname;
    String patronymic;
    Position position;

    public String toString() {
        return String.format("Сотрудник: %s %s %s %s (%s)", login, lastname, firstname, patronymic, position.getName());
    }
}
