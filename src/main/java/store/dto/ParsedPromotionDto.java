package store.dto;

public record ParsedPromotionDto(String name,
                                 int buyCount,
                                 int getCount,
                                 String startDate,
                                 String endDate) {

}
