package store.model.product;

import store.dto.DecreasePromotionQuantityDto;
import store.dto.ProductInfoDto;
import store.dto.PromotionCountDto;
import store.model.promotion.Promotion;

public class Product {
    private String name;
    private int price;
    private ProductQuantity quantity;
    private Promotion promotion;

    public Product(final String name, final int price) {
        this.name = name;
        this.price = price;
        this.quantity = new ProductQuantity();
        this.promotion = null;
    }

    public DecreasePromotionQuantityDto decreaseBoughtQuantity(final int quantity) {
        PromotionCountDto promotionCountDto = getPromotionDate();
        return this.quantity.decrease(quantity, promotionCountDto);
    }

    private PromotionCountDto getPromotionDate() {
        if (this.promotion != null) {
            return this.promotion.checkAvailablePromotion();
        }
        return null;
    }

    public ProductInfoDto getProductInformaion() {
        int normalQuantity = this.quantity.getNormal();
        int promotionQuantity = this.quantity.getPromotion();
        String promotionName = null;
        if (this.promotion != null) {
            promotionName = this.promotion.getName();
        }
        return new ProductInfoDto(this.name, this.price, normalQuantity, promotionQuantity, promotionName);
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public boolean isPromotionNull() {
        return this.promotion == null;
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
