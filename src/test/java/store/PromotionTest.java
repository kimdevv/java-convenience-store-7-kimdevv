package store;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.dto.ParsedPromotionDto;
import store.model.store.Promotions;
import store.utility.FileReader.PromotionFileReader;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PromotionTest {
    static List<ParsedPromotionDto> parsedPromotions;
    static Promotions promotions;

    @BeforeAll
    static void 프로모션_목록을_파일로부터_가져온다() {
        parsedPromotions = PromotionFileReader.inputPromotionsFromFile();
    }

    @BeforeEach
    void 프로모션_목록을_등록한다() {
        promotions = new Promotions();
        promotions.setPromotionsFromParsedString(parsedPromotions);
    }
}
