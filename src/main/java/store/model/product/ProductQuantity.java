package store.model.product;

public class ProductQuantity {
    int normalQuantity;
    int promotionQuantity;

    protected ProductQuantity() {
    }

    protected void setNormal(int quantity) {
        this.normalQuantity = quantity;
    }

    protected void setPromotion(int quantity) {
        this.promotionQuantity = quantity;
    }
}
