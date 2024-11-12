package store.utility;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class BooleanMakerTest {

    @Test
    public void Y를_입력하여_true가_반환된다() {
        boolean result = BooleanMakerFromYN.make("Y");
        assertThat(result).isTrue();
    }

    @Test
    public void N을_입력하여_false가_반환된다() {
        boolean result = BooleanMakerFromYN.make("N");
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"y", "n", "yes", "no", "예", "아니오"})
    public void Y나N이_아닌_값을_입력하면_예외를_발생시킨다(String answer) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> BooleanMakerFromYN.make(answer));
    }
}
