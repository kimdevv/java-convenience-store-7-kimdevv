package store.model.product;

import static store.constant.Constants.*;
import store.dto.DecreasePromotionQuantityDto;
import store.dto.ProductInfoDto;
import store.dto.PromotionCountDto;
import store.enumerate.ExceptionEnum;
import store.model.promotion.Promotion;

public class Product {
    private String name;
    private int price;
    private ProductQuantity quantity;
    private Promotion promotion;

    public Product(final String name, final int price) {
        validatePrice(price);
        this.name = name;
        this.price = price;
        this.quantity = new ProductQuantity();
        this.promotion = null;
    }

    public DecreasePromotionQuantityDto decreaseBoughtQuantity(final int quantity) {
        validateQuantity(quantity);
        PromotionCountDto promotionCountDto = getPromotionDate();
        return this.quantity.decrease(quantity, promotionCountDto);
    }

    private PromotionCountDto getPromotionDate() {
        if (this.promotion != null) {
            return this.promotion.checkAvailablePromotion();
        }
        return null;
    }

    public ProductInfoDto getProductInformation() {
        int normalQuantity = this.quantity.getNormal();
        int promotionQuantity = this.quantity.getPromotion();
        String promotionName = null;
        if (this.promotion != null) {
            promotionName = this.promotion.getName();
        }
        return new ProductInfoDto(this.name, this.price, normalQuantity, promotionQuantity, promotionName);
    }

    private void validatePrice(int price) {
        if (price <= MINIMUN_PRICE_OF_PRODUCT) {
            throw new IllegalArgumentException(ExceptionEnum.WRONG_INPUT.getMessage());
        }
    }

    private void validateQuantity(int quantity) {
        if (quantity <= MINIMUN_QUANTITY_OF_PRODUCT) {
            throw new IllegalArgumentException(ExceptionEnum.WRONG_INPUT.getMessage());
        }
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
