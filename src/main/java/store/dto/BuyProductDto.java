package store.dto;

import store.enumerate.ExceptionEnum;

public class BuyProductDto {
    private String name;
    private int quantity;
    private int unitPrice;
    private int freeQuantity;
    private int lackQuantity;
    private int needQuantity;

    public BuyProductDto(String name, int quantity, int unitPrice
            , int freeQuantity, int lackQuantity, int needQuantity) {
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.freeQuantity = freeQuantity;
        this.lackQuantity = lackQuantity;
        this.needQuantity = needQuantity;
    }

    public void decreaseLackQuantity() {
        if (this.lackQuantity < 1) {
            throw new IllegalArgumentException(ExceptionEnum.NO_DECREASABLE_COUNT.getMessage());
        }
        this.quantity -= this.lackQuantity;
        this.lackQuantity = 0;
    }

    public void decreaseNeedQuantity() {
        if (this.needQuantity < 1) {
            throw new IllegalArgumentException(ExceptionEnum.NO_DECREASABLE_COUNT.getMessage());
        }
        this.freeQuantity += this.needQuantity;
        this.needQuantity = 0;
    }

    public String getName() {
        return this.name;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public int getUnitPrice() {
        return this.unitPrice;
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
