package store.model.product;

import store.dto.DecreasePromotionQuantityDto;
import store.dto.PromotionCountDto;
import store.enumerate.ExceptionEnum;
import store.utility.ExceptionThrower;

public class ProductQuantity {
    int normalQuantity;
    int promotionQuantity;

    protected ProductQuantity() {
    }

    protected DecreasePromotionQuantityDto decrease(int quantity, PromotionCountDto promotionCountDto) {
        int buyCount = promotionCountDto.buyCount(), getCount = promotionCountDto.getCount();

        if (promotionCountDto != null) {
            int[] freeAndLackAndRemain = decreasePromotionQuantity(quantity, buyCount, getCount);
            int needQuantity = calculateNeedQuantity(quantity, buyCount, getCount);
            decreaseNormalQuantity(freeAndLackAndRemain[2]);
            return new DecreasePromotionQuantityDto(freeAndLackAndRemain[0], freeAndLackAndRemain[1], needQuantity);
        }
        decreaseNormalQuantity(quantity);
        return new DecreasePromotionQuantityDto(0, 0, 0);
    }

    private void decreaseNormalQuantity(int quantity) {
        validateAvailableDecrease(quantity);
        this.normalQuantity -= quantity;
    }

    private int calculateNeedQuantity(int quantity, int buyCount, int getCount) {
        if (buyCount + getCount > quantity) {
            return buyCount + getCount - quantity;
        }
        return 0;
    }

    private int[] decreasePromotionQuantity(int quantity, int buyCount, int getCount) {
        int freeQuantity = promotionQuantity / (buyCount+getCount) * getCount;
        promotionQuantity -= quantity;

        if (quantity > promotionQuantity) {
            int lackQuantity = quantity - promotionQuantity / (buyCount+getCount);
            int remainQuantity = quantity - promotionQuantity;
            promotionQuantity = 0;
            return new int[] {freeQuantity, lackQuantity, remainQuantity};
        }
        return new int[] {freeQuantity, 0, 0};
    }

    private void validateAvailableDecrease(int quantity) {
        if (quantity > normalQuantity) {
            ExceptionThrower.throwing(ExceptionEnum.TOO_MANY_COUNT);
        }
    }

    protected void setNormal(int quantity) {
        this.normalQuantity = quantity;
    }

    protected void setPromotion(int quantity) {
        this.promotionQuantity = quantity;
    }
}
