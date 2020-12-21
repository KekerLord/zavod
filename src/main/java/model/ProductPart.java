package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductPart {
    private Long partId;
    private Boolean installed = false;

    public ProductPart(Long partId) {
        this.partId = partId;
    }

    public String toString() {
        return String.format("Деталь %d: %s", partId, installed ? "установлено" : "не установлено");
    }
}
