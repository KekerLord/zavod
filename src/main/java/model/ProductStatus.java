package model;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public enum ProductStatus {
    UNREADY("не готово"), ACCEPTED("принято"), REJECTED("брак");

    private final String status;
}
