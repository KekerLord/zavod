package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Product implements Entity {
    private Long id;
    private String name;
    private List<Long> parts;

    public String toString() {
        return String.format("Изделие: %d %s %s", id, name, parts);
    }
}