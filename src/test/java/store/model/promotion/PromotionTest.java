package store.model.promotion;

import org.junit.jupiter.api.Test;
import store.dto.PromotionCountDto;

import static org.assertj.core.api.Assertions.*;

public class PromotionTest {

    @Test
    public void 오늘_날짜가_행사_날짜에_포함되어_그_개수_정보를_알려준다() {
        Promotion promotion = new Promotion("2+1행사",
                2, 1, "2024-01-01", "2024-12-31");
        assertThat(promotion.checkAvailablePromotion())
                .isEqualTo(new PromotionCountDto(2,1));
    }

    @Test
    public void 오늘_날짜가_행사_날짜에_포함되지_않으므로_null을_반환한다() {
        Promotion promotion = new Promotion("2+1행사",
                2, 1, "2023-01-01", "2023-12-31");
        assertThat(promotion.checkAvailablePromotion())
                .isEqualTo(null);
    }
}
