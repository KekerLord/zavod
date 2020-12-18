package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Part {
    private Long id;
    private String name;
    private Long quantity;

    public String toString() {
        return String.format("Деталь: %d %s %d", id, name, quantity);
    }
}

