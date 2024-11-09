package store.dto;

public record BuyProductDto(String name,
                            int quantity,
                            int unitPrice,
                            int freeQuantity,
                            int lackQuantity,
                            int needQuantity) {

}
