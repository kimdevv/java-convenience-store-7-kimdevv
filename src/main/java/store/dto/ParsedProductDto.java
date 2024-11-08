package store.dto;

public class ParsedProductDto {
    private String name;
    private int price;
    private int quantity;
    private String promotion;

    public ParsedProductDto(String productName, int productPrice, int productQuantity, String productPromotion) {
        this.name = productName;
        this.price = productPrice;
        this.quantity = productQuantity;
        this.promotion = productPromotion;
    }
}
