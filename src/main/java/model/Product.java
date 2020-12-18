package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Product {
    private Long id;
    private String name;
    private List<Long> parts;

    public String toString() {
        return String.format("Деталь: %d %s %s", id, name, parts);
    }
}