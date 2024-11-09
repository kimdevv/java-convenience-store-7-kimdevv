package store.model.store;

import store.dto.ParsedPromotionDto;
import store.model.promotion.Promotion;

import java.util.ArrayList;
import java.util.List;

public class Promotions {
    private List<Promotion> promotions;

    public void setPromotionsFromParsedString(List<ParsedPromotionDto> parsedPromotions) {
        promotions = new ArrayList<>();

        for (ParsedPromotionDto parsedPromotion : parsedPromotions) {
            String promotionName = parsedPromotion.name();
            int buyCount = parsedPromotion.buyCount();
            int getCount = parsedPromotion.getCount();
            String startDate = parsedPromotion.startDate();
            String endDate = parsedPromotion.endDate();
            this.promotions.add(new Promotion(promotionName, buyCount, getCount, startDate, endDate));
        }
    }

    public Promotion findPromotionByPromotionName(String name) {
        return this.promotions.stream()
                .filter(promotion -> promotion.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
