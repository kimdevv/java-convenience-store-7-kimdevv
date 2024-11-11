package store.utility;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import store.dto.BuyProductParseDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class BuyProductParserTest {
    @Test
    public void 구매할_상품_목록을_입력하면_정해진_형식으로_파싱한다() {
        List<BuyProductParseDto> result = BuyProductParser.parse("[콜라-5],[감자칩-1],[물-3]");

        assertThat(result.size()).isEqualTo(3);
        assertThat(result.get(0)).isEqualTo(new BuyProductParseDto("콜라", 5));
        assertThat(result.get(1)).isEqualTo(new BuyProductParseDto("감자칩", 1));
        assertThat(result.get(2)).isEqualTo(new BuyProductParseDto("물", 3));
    }

    @ParameterizedTest
    @ValueSource(strings = {"[콜라-5", "콜라-5]", "[콜라5]", "콜라-5", "[콜라-a]",
            "[콜라-5],사이다-2]", "콜라-5,사이다-2", "[콜라-5][사이다-2]"})
    void 잘못된_형식으로_입력할_경우_예외를_발생시킨다(String rawBuyProducts) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> BuyProductParser.parse(rawBuyProducts));
    }
}
