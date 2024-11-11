package store.model.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.dto.DecreasePromotionQuantityDto;
import store.dto.ProductInfoDto;
import store.model.promotion.Promotion;

import static org.assertj.core.api.Assertions.*;

public class ProductTest {

    private static Product product;

    @BeforeEach
    public void Product_객체를_생성한다() {
        product = new Product("테스트상품", 5000);
        product.setPromotion(new Promotion("2+1행사",
                2, 1, "2024-01-01", "2024-12-31"));
        product.setNormalQuantity(50);
        product.setPromotionQuantity(20);
    }

    @Test
    public void 가격을_0이하로_입력하여_예외가_발생한다() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Product("예외 발생 상품", 0));
    }

    @Test
    public void 상품_객체_정보를_받아온다() {
        ProductInfoDto productInfoDto = product.getProductInformation();
        assertThat(productInfoDto).isEqualTo(new ProductInfoDto("테스트상품",
                5000, 50, 20, "2+1행사"));
    }

    @Test
    public void 상품을_구매한다() {
        DecreasePromotionQuantityDto decreaseResult = product.decreaseBoughtQuantity(30);
        assertThat(decreaseResult).isEqualTo(new DecreasePromotionQuantityDto(6, 12, 0));
    }

    @Test
    public void 구입할_상품_개수를_0이하로_입력하여_예외가_발생한다() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> product.decreaseBoughtQuantity(0));
    }
}
