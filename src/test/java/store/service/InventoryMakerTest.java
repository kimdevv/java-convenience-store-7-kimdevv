package store.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import store.dto.ParsedProductDto;
import store.model.product.Product;
import store.model.store.Promotions;
import store.utility.FileReader.InventoryFileReader;
import store.utility.FileReader.PromotionFileReader;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

public class InventoryMakerTest {
    static List<ParsedProductDto> parsedProducts;
    static InventoryMaker inventoryMaker;

    @BeforeAll
    static void 재고_목록을_파일로부터_가져온다() {
        Promotions promotions = new Promotions();
        promotions.setPromotionsFromParsedString(PromotionFileReader.inputPromotionsFromFile());

        parsedProducts = InventoryFileReader.inputInventoryFromFile();
        inventoryMaker = new InventoryMaker(promotions);
    }

    @Test
    void 재고_목록을_등록한_리스트를_가져온다() {
        List<Product> allProducts = inventoryMaker.setInventoryFromParsedString(parsedProducts);
        assertThat(allProducts.size()).isEqualTo(11);
    }

}
