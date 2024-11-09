package store.model.product;

import store.dto.DecreasePromotionQuantityDto;
import store.dto.PromotionCountDto;
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

    private void decreaseBoughtQuantity(int quantity) {
        PromotionCountDto promotionCountDto = this.promotion.checkAvailablePromotion();
        DecreasePromotionQuantityDto decreasePromotionQuantityDto = this.quantity.decrease(quantity, promotionCountDto);
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
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
