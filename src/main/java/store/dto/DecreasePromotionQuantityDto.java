package store.dto;

public class DecreasePromotionQuantityDto {
    private int freeQuantity;
    private int lackQuantity;
    private int needQuantity;

    public DecreasePromotionQuantityDto() {}
    public DecreasePromotionQuantityDto(final int freeQuantity, final int lackQuantity, final int needQuantity) {
        this.freeQuantity = freeQuantity;
        this.lackQuantity = lackQuantity;
        this.needQuantity = needQuantity;
    }

    public int getFreeQuantity() {
        return this.freeQuantity;
    }

    public int getLackQuantity() {
        return this.lackQuantity;
    }

    public int getNeedQuantity() {
        return this.needQuantity;
    }
}
