package store.model.promotion;

public class PromotionCount {
    private int buyCount;
    private int getCount;

    protected PromotionCount(int buyCount, int getCount) {
        this.buyCount = buyCount;
        this.getCount = getCount;
    }
}
