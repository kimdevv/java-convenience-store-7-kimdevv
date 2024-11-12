package store.model.store;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.dto.ParsedPromotionDto;
import store.model.promotion.Promotion;
import store.model.store.Promotions;
import store.utility.FileReader.PromotionFileReader;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PromotionsTest {
    private static List<ParsedPromotionDto> parsedPromotions;
    private static Promotions promotions;

    @BeforeAll
    public static void 프로모션_목록을_파일로부터_가져온다() {
        parsedPromotions = PromotionFileReader.inputPromotionsFromFile();
    }

    @BeforeEach
    public void 프로모션_목록을_등록한다() {
        promotions = new Promotions();
        promotions.setPromotionsFromParsedString(parsedPromotions);
    }

    @Test
    public void 이름으로_프로모션_객체를_검색한다() {
        String promotionName = "탄산2+1";
        Promotion foundPromotion = promotions.findPromotionByPromotionName(promotionName);
        assertThat(foundPromotion.getName()).isEqualTo(promotionName);
    }

    @Test
    public void 존재하지_않는_프로모션_이름으로_검색하면_null을_반환한다() {
        String promotionName = "탄산5000+1";
        Promotion foundPromotion = promotions.findPromotionByPromotionName(promotionName);
        assertThat(foundPromotion).isNull();
    }
}
