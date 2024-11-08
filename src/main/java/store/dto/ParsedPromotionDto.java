package store.dto;

public class ParsedPromotionDto {
    private String name;
    private int buyCount;
    private int getCount;
    private String startDate;
    private String endDate;

    public ParsedPromotionDto(String promotionName, int buyCount, int getCount, String startDate, String endDate) {
        this.name = promotionName;
        this.buyCount = buyCount;
        this.getCount = getCount;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
