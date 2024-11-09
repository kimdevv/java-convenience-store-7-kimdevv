package store.model.product;

import store.model.promotion.Promotion;

public class Product {
    private String name;
    private int price;
    private ProductQuantity quantity;
    private Promotion promotion;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
        this.quantity = new ProductQuantity();
        this.promotion = null;
    }

    public String getName() {
        return this.name;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public void setNormalQuantity(int quantity) {
        this.quantity.setNormal(quantity);
    }

    public void setPromotionQuantity(int quantity) {
        this.quantity.setPromotion(quantity);
    }
}
