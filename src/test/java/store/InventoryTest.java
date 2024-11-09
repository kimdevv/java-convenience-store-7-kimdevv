package store;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import store.service.InventoryMaker;
import store.dto.ParsedProductDto;
import store.model.product.Product;
import store.model.store.Promotions;
import store.utility.FileReader.InventoryFileReader;
import store.utility.FileReader.PromotionFileReader;

import java.util.List;

public class InventoryTest {
    static List<ParsedProductDto> parsedProducts;
    static InventoryMaker inventoryMaker;

    @BeforeAll
    static void 재고_목록을_파일로부터_가져온다() {
        Promotions promotions = new Promotions();
        promotions.setPromotionsFromParsedString(PromotionFileReader.inputPromotionsFromFile());

        parsedProducts = InventoryFileReader.inputInventoryFromFile();
        inventoryMaker = new InventoryMaker(promotions);
    }

    @BeforeEach
    void 재고_목록을_등록한_리스트를_가져온다() {
        List<Product> allProducts = inventoryMaker.setInventoryFromParsedString(parsedProducts);
    }

}
