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
}
