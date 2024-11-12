package store.dto;

public record ProductInfoDto(String name, int price,
                             int normalQuantity, int promotionQuantity, String promotionName) {
}
