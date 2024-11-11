package store.utility;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MembershipSaleCalculatorTest {

    @Test
    public void 멤버십_할인_금액을_계산한다() {
        int originalTotalPrice = 5000;
        int membershipSalePrice = MembershipSaleCalculator.calculate(originalTotalPrice);
        assertThat(membershipSalePrice).isEqualTo(1500);
    }

    @Test
    public void 멤버십_할인의_최대금액은_8000원이다() {
        int originalTotalPrice = 50000;
        int membershipSalePrice = MembershipSaleCalculator.calculate(originalTotalPrice);
        assertThat(membershipSalePrice).isEqualTo(8000);
    }
}
