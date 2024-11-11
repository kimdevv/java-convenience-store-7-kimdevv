package store.model.product;

import static store.constant.Constants.*;
import store.dto.DecreasePromotionQuantityDto;
import store.dto.PromotionCountDto;
import store.enumerate.ExceptionEnum;

public class ProductQuantity {
    private int normalQuantity;
    private int promotionQuantity;

    protected ProductQuantity() {
    }

    protected DecreasePromotionQuantityDto decrease(int quantity, PromotionCountDto promotionCountDto) {
        validateAvailableDecrease(quantity);
        if (promotionCountDto != null) {
            return decreasePromotionQuantity(quantity, promotionCountDto);
        }
        decreaseNormalQuantity(quantity);
        return new DecreasePromotionQuantityDto(
                MINIMUN_QUANTITY_OF_PRODUCT, MINIMUN_QUANTITY_OF_PRODUCT, MINIMUN_QUANTITY_OF_PRODUCT);
    }

    private DecreasePromotionQuantityDto decreasePromotionQuantity(int quantity, PromotionCountDto promotionCountDto) {
        int buyCount = promotionCountDto.buyCount(), getCount = promotionCountDto.getCount();
        int[] freeAndLackAndRemain = calculateFreeAndLackAndRemainQuantity(quantity, buyCount, getCount);
        int needQuantity = calculateNeedQuantity(quantity, freeAndLackAndRemain[0], buyCount, getCount);
        decreaseNormalQuantity(freeAndLackAndRemain[2]);
        return new DecreasePromotionQuantityDto(freeAndLackAndRemain[0], freeAndLackAndRemain[1], needQuantity);
    }

    private void decreaseNormalQuantity(int quantity) {
        this.normalQuantity -= quantity;
    }

    private int calculateNeedQuantity(int quantity, int freeQuantity, int buyCount, int getCount) {
        int remainPromotionQuantity = quantity - freeQuantity * (buyCount+getCount);
        if (remainPromotionQuantity == buyCount && this.promotionQuantity >= getCount) {
            return getCount;
        }
        return 0;
    }

    private int[] calculateFreeAndLackAndRemainQuantity(int quantity, int buyCount, int getCount) {
        if (quantity >= promotionQuantity) {
            int freeQuantity = promotionQuantity / (buyCount + getCount) * getCount;
            int lackQuantity = quantity - (freeQuantity * (buyCount+getCount));
            int remainQuantity = quantity - promotionQuantity;
            promotionQuantity = MINIMUN_QUANTITY_OF_PRODUCT;
            return new int[] {freeQuantity, lackQuantity, remainQuantity};
        }
        int freeQuantity = quantity / (buyCount+getCount) * getCount;
        promotionQuantity -= quantity;
        return new int[] {freeQuantity, MINIMUN_QUANTITY_OF_PRODUCT, MINIMUN_QUANTITY_OF_PRODUCT};
    }

    private void validateAvailableDecrease(int quantity) {
        if (quantity > normalQuantity + promotionQuantity) {
            throw new IllegalArgumentException(ExceptionEnum.TOO_MANY_COUNT.getMessage());
        }
    }

    protected int getNormal() {
        return this.normalQuantity;
    }

    protected int getPromotion() {
        return this.promotionQuantity;
    }

    protected void setNormal(int quantity) {
        this.normalQuantity = quantity;
    }

    protected void setPromotion(int quantity) {
        this.promotionQuantity = quantity;
    }
}
