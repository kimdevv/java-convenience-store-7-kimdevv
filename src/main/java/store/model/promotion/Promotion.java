package store.model.promotion;

import store.dto.PromotionCountDto;

public class Promotion {
    private String name;
    PromotionCount count;
    PromotionDate date;

    public Promotion(String name, int buyCount, int getCount, String startDate, String endDate) {
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
