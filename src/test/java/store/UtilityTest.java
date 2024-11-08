package store;

import org.junit.jupiter.api.Test;
import store.dto.ParsedProductDto;
import store.utility.InventoryFileReader;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class UtilityTest {

    @Test
    void 재고_목록을_파일로부터_가져온다() {
        List<ParsedProductDto> parsedProducts = InventoryFileReader.inputInventoryFromFile();
        assertThat(parsedProducts.size() > 0);
    }
}