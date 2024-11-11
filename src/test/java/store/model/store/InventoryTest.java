package store.model.store;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.dto.BuyProductDto;
import store.dto.ParsedProductDto;
import store.dto.ProductInfoDto;
import store.model.product.Product;
import store.service.InventoryMaker;
import store.utility.FileReader.InventoryFileReader;
import store.utility.FileReader.PromotionFileReader;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class InventoryTest {
    private static List<Product> allProducts;
    private static Inventory inventory;

    @BeforeAll
    public static void 재고_목록을_파일로부터_가져온다() {
        Promotions promotions = new Promotions();
        promotions.setPromotionsFromParsedString(PromotionFileReader.inputPromotionsFromFile());

        List<ParsedProductDto> parsedProducts = InventoryFileReader.inputInventoryFromFile();
        InventoryMaker inventoryMaker = new InventoryMaker(promotions);
        allProducts = inventoryMaker.setInventoryFromParsedString(parsedProducts);
    }

    @BeforeEach
    public void Inventory_객체를_초기화한다() {
        inventory = new Inventory(allProducts);
    }

    public void 상품_구매_테스트_메서드(BuyProductDto buyProductDto, String name,
                              int quantity, int unitPrice, int freeQuantity, int lackQuantity, int needQuantity) {
        assertThat(buyProductDto.name()).isEqualTo(name);
        assertThat(buyProductDto.quantity()).isEqualTo(quantity);
        assertThat(buyProductDto.unitPrice()).isEqualTo(unitPrice);
        assertThat(buyProductDto.freeQuantity()).isEqualTo(freeQuantity);
        assertThat(buyProductDto.lackQuantity()).isEqualTo(lackQuantity);
        assertThat(buyProductDto.needQuantity()).isEqualTo(needQuantity);
    }

    @Test
    public void 상품을_구매한다() {
        BuyProductDto buyProductDto = inventory.buy("물", 5);
        상품_구매_테스트_메서드(buyProductDto,"물",
                5, 500, 0, 0, 0);
    }

    @Test
    public void 프로모션_상품을_구매한다() {
        BuyProductDto buyProductDto = inventory.buy("콜라", 6);
        상품_구매_테스트_메서드(buyProductDto,"콜라",
                6, 1000, 2, 0, 0);
    }

    @Test
    public void 프로모션_상품을_구매한다2() {
        BuyProductDto buyProductDto = inventory.buy("콜라", 10);
        상품_구매_테스트_메서드(buyProductDto,"콜라",
                10, 1000, 3, 1, 0);
    }

    @Test
    public void 프로모션_상품을_구매한다3() {
        BuyProductDto buyProductDto = inventory.buy("콜라", 20);
        상품_구매_테스트_메서드(buyProductDto,"콜라",
                20, 1000, 3, 11, 0);
    }

    @Test
    public void 프로모션_상품을_부족하게구매한다() {
        BuyProductDto buyProductDto = inventory.buy("콜라", 2);
        상품_구매_테스트_메서드(buyProductDto,"콜라",
                2, 1000, 0, 0, 1);
    }

    @Test
    public void 프로모션_상품의_재고가_부족할_경우() {
        BuyProductDto buyProductDto = inventory.buy("콜라", 15);
        상품_구매_테스트_메서드(buyProductDto,"콜라",
                15, 1000, 3, 6, 0);
    }

    @Test
    public void 구입할_수_없는_수량을_입력했을_경우() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> inventory.buy("콜라", 25))
                .withMessageStartingWith("[ERROR]");
    }

    @Test
    public void 편의점의_모든_상품_정보를_가져온다() {
        List<ProductInfoDto> products = inventory.getAllProductsInfomation();
        assertThat(products.size()).isEqualTo(11);
    }

    @Test
    public void 프로모션을_적용받지_못하는_상품은_제외하고_구입한다() {
        BuyProductDto originalBuyProduct = new BuyProductDto("콜라",
                12, 1000, 3, 3, 0);
        BuyProductDto newBuyProduct = inventory.decreaseLackQuantity(originalBuyProduct);
        assertThat(newBuyProduct).isEqualTo(new BuyProductDto("콜라",
                9, 1000, 3, 0, 0));
    }

    @Test
    public void 프로모션을_적용받을_수_있는_수량만큼_구입수량을_추가한다() {
        BuyProductDto originalBuyProduct = new BuyProductDto("콜라",
                8, 1000, 2, 0, 1);
        BuyProductDto newBuyProduct = inventory.increaseNeedQuantity(originalBuyProduct);
        assertThat(newBuyProduct).isEqualTo(new BuyProductDto("콜라",
                9, 1000, 3, 0, 0));
    }
}
