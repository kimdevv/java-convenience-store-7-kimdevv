package store.model.promotion;

import store.dto.PromotionCountDto;

public class Promotion {
    private String name;
    PromotionCount count;
    PromotionDate date;

    public Promotion(final String name, final int buyCount,
                     final int getCount, final String startDate, final String endDate) {
        this.name = name;
        this.count = new PromotionCount(buyCount, getCount);
        this.date = new PromotionDate(startDate, endDate);
    }

    public PromotionCountDto checkAvailablePromotion() {
        if (this.date.isTodayInPromotionDate()) {
            return new PromotionCountDto(this.count.getBuyCount(), this.count.getGetCount());
        }
        return null;
    }

    public String getName() {
        return this.name;
    }
}
