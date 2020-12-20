package db;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DatabasePaths {
    PARTS("parts.json"),
    PRODUCTS("products.json"),
    EMPLOYEES("employees.json");

    private final String filePath;
}
