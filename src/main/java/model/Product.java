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
    private List<ProductPart> parts;
    private ProductStatus status = ProductStatus.UNREADY;


    public String toString() {
        return String.format("Изделие: %d %s %s %s", id, name, parts,status.getStatus());
    }
}