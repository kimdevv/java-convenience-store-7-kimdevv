package store.controller;

import store.dto.ParsedProductDto;
import store.dto.ProductInfoDto;
import store.model.product.Product;
import store.model.store.Inventory;
import store.model.store.Promotions;
import store.service.InventoryMaker;
import store.utility.FileReader.InventoryFileReader;
import store.utility.FileReader.PromotionFileReader;
import store.view.OutputView;

import java.util.List;

public class StoreController {
    public void openConvenienceStore() {
        Promotions promotions = setPromotionFromFile();
        Inventory inventory = setInventoryFromFile(promotions);

        outputStoreGreeting(inventory.getAllProductsInfomation());
    }

    private Promotions setPromotionFromFile() {
        Promotions promotions = new Promotions();
        promotions.setPromotionsFromParsedString(PromotionFileReader.inputPromotionsFromFile());
        return promotions;
    }

    private Inventory setInventoryFromFile(Promotions promotions) {
        List<ParsedProductDto> parsedProducts = InventoryFileReader.inputInventoryFromFile();
        InventoryMaker inventoryMaker = new InventoryMaker(promotions);
        List<Product> allProducts = inventoryMaker.setInventoryFromParsedString(parsedProducts);
        return new Inventory(allProducts);
    }

    private void outputStoreGreeting(List<ProductInfoDto> allProducts) {
        OutputView.outputWelcomeMessage();
        OutputView.outputCurrentInventory(allProducts);
    }
}
