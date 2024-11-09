package store.model.store;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    static void Inventory_객체를_초기화한다() {
        inventory = new Inventory(allProducts);
    }
}
