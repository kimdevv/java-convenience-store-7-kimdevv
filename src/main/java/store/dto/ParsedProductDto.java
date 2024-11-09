package store.dto;

public record ParsedProductDto(String name,
                               int price,
                               int quantity,
                               String promotion) {
}
