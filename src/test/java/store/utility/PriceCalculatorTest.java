package store.utility;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.dto.BillingDto;
import store.dto.BuyProductDto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

public class PriceCalculatorTest {
    public static List<BuyProductDto> boughtProducts;

    @BeforeEach
    public void 구입한_물건_정보를_초기화한다() {
        boughtProducts = List.of(
                new BuyProductDto("콜라", 10, 1000, 3, 1, 0),
                new BuyProductDto("사이다", 3, 1000, 1, 0, 0),
                new BuyProductDto("생수", 5, 500, 0, 0, 0));
    }

    @Test
    public void 멤버십_없이_가격정보를_모두_계산하라() {
        BillingDto billingResult = PriceCalculator.calculate(boughtProducts, false);
        assertThat(billingResult.totalCount()).isEqualTo(18);
        assertThat(billingResult.totalPrice()).isEqualTo(15500);
        assertThat(billingResult.promotionSale()).isEqualTo(4000);
        assertThat(billingResult.membershipSale()).isEqualTo(0);
        assertThat(billingResult.payCost()).isEqualTo(11500);
    }

    @Test
    public void 멤버십_할인을_받고_가격정보를_모두_계산하라() {
        BillingDto billingResult = PriceCalculator.calculate(boughtProducts, true);
        assertThat(billingResult.totalCount()).isEqualTo(18);
        assertThat(billingResult.totalPrice()).isEqualTo(15500);
        assertThat(billingResult.promotionSale()).isEqualTo(4000);
        assertThat(billingResult.membershipSale()).isEqualTo(3450);
        assertThat(billingResult.payCost()).isEqualTo(8050);
    }
}
