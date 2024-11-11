package store.utility;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class InputValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"Y", "N"})
    public void 입력이_Y나_N이라면_검증을_통과한다(String answer) {
        InputValidator.validateYesNo(answer);
    }

    @ParameterizedTest
    @ValueSource(strings = {"y", "n", "yes", "no", "예", "아니오"})
    public void 그_외의_입력값은_예외를_발생시킨다(String answer) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> InputValidator.validateYesNo(answer));
    }


    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100, 1000})
    public void 수량이_0보다_크면_검증을_통과한다(int quantity) {
        InputValidator.validateQuantityUnderMinimumCount(quantity);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -5})
    public void 수량이_0_이하이면_예외를_발생시킨다(int quantity) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> InputValidator.validateQuantityUnderMinimumCount(quantity));
    }
}
