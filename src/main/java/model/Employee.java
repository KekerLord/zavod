package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Employee implements Entity {
    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private Position position;

    public String toString() {
        return String.format("Сотрудник: %s %s %s %s (%s)", id, lastName, firstName, patronymic, position.getName());
    }
}
