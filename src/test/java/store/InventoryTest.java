package store;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import store.dto.ParsedProductDto;
import store.utility.FileReader.InventoryFileReader;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class InventoryTest {
    static List<ParsedProductDto> parsedProducts;
    static Inventory inventory;

    @BeforeAll
    static void 재고_목록을_파일로부터_가져온다() {
        parsedProducts = InventoryFileReader.inputInventoryFromFile();
    }

    @BeforeEach
    void 재고_목록을_등록한다() {
        inventory = new Inventory();
        inventory.setInventoryFromParsedString(parsedProducts);
    }

}
