package store.model.promotion;

public class Promotion {
    private String name;
    PromotionCount count;
    PromotionDate date;

    public Promotion(String name, int buyCount, int getCount, String startDate, String endDate) {
        this.name = name;
        this.count = new PromotionCount(buyCount, getCount);
        this.date = new PromotionDate(startDate, endDate);
    }

    public String getName() {
        return this.name;
    }
}
