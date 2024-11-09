package store.model.store;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.dto.BuyProductDto;
import store.dto.ParsedProductDto;
import store.model.product.Product;
import store.service.InventoryMaker;
import store.utility.FileReader.InventoryFileReader;
import store.utility.FileReader.PromotionFileReader;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class InventoryTest {

    static List<Product> allProducts;
    static Inventory inventory;

    @BeforeAll
    static void 재고_목록을_파일로부터_가져온다() {
        Promotions promotions = new Promotions();
        promotions.setPromotionsFromParsedString(PromotionFileReader.inputPromotionsFromFile());

        List<ParsedProductDto> parsedProducts = InventoryFileReader.inputInventoryFromFile();
        InventoryMaker inventoryMaker = new InventoryMaker(promotions);
        allProducts = inventoryMaker.setInventoryFromParsedString(parsedProducts);
    }

    @BeforeEach
    void Inventory_객체를_초기화한다() {
        inventory = new Inventory(allProducts);
    }

    @Test
    void 상품을_구매한다() {
        BuyProductDto buyProductDto = inventory.buy("물", 5);
        assertThat(buyProductDto.name()).isEqualTo("물");
        assertThat(buyProductDto.quantity()).isEqualTo(5);
        assertThat(buyProductDto.unitPrice()).isEqualTo(500);
        assertThat(buyProductDto.freeQuantity()).isEqualTo(0);
        assertThat(buyProductDto.lackQuantity()).isEqualTo(0);
        assertThat(buyProductDto.needQuantity()).isEqualTo(0);
    }

    @Test
    void 프로모션_상품을_구매한다() {
        BuyProductDto buyProductDto = inventory.buy("콜라", 6);
        assertThat(buyProductDto.name()).isEqualTo("콜라");
        assertThat(buyProductDto.quantity()).isEqualTo(6);
        assertThat(buyProductDto.unitPrice()).isEqualTo(1000);
        assertThat(buyProductDto.freeQuantity()).isEqualTo(2);
        assertThat(buyProductDto.lackQuantity()).isEqualTo(0);
        assertThat(buyProductDto.needQuantity()).isEqualTo(0);
    }

    @Test
    void 프로모션_상품을_부족하게구매한다() {
        BuyProductDto buyProductDto = inventory.buy("콜라", 2);
        assertThat(buyProductDto.name()).isEqualTo("콜라");
        assertThat(buyProductDto.quantity()).isEqualTo(2);
        assertThat(buyProductDto.unitPrice()).isEqualTo(1000);
        assertThat(buyProductDto.freeQuantity()).isEqualTo(0);
        assertThat(buyProductDto.lackQuantity()).isEqualTo(0);
        assertThat(buyProductDto.needQuantity()).isEqualTo(1);
    }

    @Test
    void 프로모션_상품의_재고가_부족할_경우() {
        BuyProductDto buyProductDto = inventory.buy("콜라", 15);
        assertThat(buyProductDto.name()).isEqualTo("콜라");
        assertThat(buyProductDto.quantity()).isEqualTo(15);
        assertThat(buyProductDto.unitPrice()).isEqualTo(1000);
        assertThat(buyProductDto.freeQuantity()).isEqualTo(3);
        assertThat(buyProductDto.lackQuantity()).isEqualTo(6);
        assertThat(buyProductDto.needQuantity()).isEqualTo(0);
    }
}
