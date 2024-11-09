package store.utility;

import org.junit.jupiter.api.Test;
import store.dto.ParsedProductDto;
import store.dto.ParsedPromotionDto;
import store.utility.FileReader.InventoryFileReader;
import store.utility.FileReader.PromotionFileReader;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class UtilityTest {

    @Test
    void 재고_목록을_파일로부터_가져온다() {
        List<ParsedProductDto> parsedProducts = InventoryFileReader.inputInventoryFromFile();
        assertThat(parsedProducts.size() > 0);
    }

    @Test
    void 프로모션_목록을_파일로부터_가져온다() {
        List<ParsedPromotionDto> parsedPromotions = PromotionFileReader.inputPromotionsFromFile();
        assertThat(parsedPromotions.size() > 0);
    }

    @Test
    void 구매할_상품_목록을_입력하면_정해진_형식으로_파싱한다() {
        List<BuyProductParseDto> result = BuyProductParser.parse("[콜라-5],[감자칩-1],[물-3]");

        assertThat(result.size()).isEqualTo(3);
        assertThat(result.get(0)).isEqualTo(new BuyProductParseDto("콜라", 5));
        assertThat(result.get(1)).isEqualTo(new BuyProductParseDto("감자칩", 1));
        assertThat(result.get(2)).isEqualTo(new BuyProductParseDto("물", 3));
    }
}
