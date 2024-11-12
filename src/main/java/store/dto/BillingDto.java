package store.dto;

public record BillingDto(int totalCount, int totalPrice
        , int promotionSale, int membershipSale, int payCost) {
}
