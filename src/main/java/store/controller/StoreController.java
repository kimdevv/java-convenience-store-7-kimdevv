package store.controller;

import store.dto.BuyProductDto;
import store.dto.BuyProductParseDto;
import store.dto.ParsedProductDto;
import store.dto.ProductInfoDto;
import store.model.product.Product;
import store.model.store.Inventory;
import store.model.store.Promotions;
import store.service.InventoryMaker;
import store.utility.BuyProductParser;
import store.utility.FileReader.InventoryFileReader;
import store.utility.FileReader.PromotionFileReader;
import store.utility.InputValidator;
import store.view.InputView;
import store.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class StoreController {
    public void openConvenienceStore() {
        Promotions promotions = setPromotionFromFile();
        Inventory inventory = setInventoryFromFile(promotions);

        outputStoreGreeting(inventory.getAllProductsInfomation());

        List<BuyProductDto> boughtProducts = buyProduct(inventory);

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

    private List<BuyProductDto> buyProduct(Inventory inventory) {
        List<BuyProductDto> boughtProducts = new ArrayList<>();

        List<BuyProductParseDto> rawBuyProducts = BuyProductParser.parse(InputView.inputBuyProducts());
        for (BuyProductParseDto rawBuyProduct : rawBuyProducts) {
            BuyProductDto boughtProduct = inventory.buy(rawBuyProduct.name(), rawBuyProduct.quantity());
            askNoPromotionSale(boughtProduct);
            askBuyMoreProduct(boughtProduct);
            boughtProducts.add(boughtProduct);
        }
        return boughtProducts;
    }

    private void askNoPromotionSale(BuyProductDto boughtProduct) {
        if (boughtProduct.getLackQuantity() < 0) {
            return;
        }

        String answer = InputView.inputNoPromotionSale(boughtProduct.getName(), boughtProduct.getLackQuantity());
        InputValidator.validateYesNo(answer);
        if (answer.equals("Y")) {
            boughtProduct.decreaseLackQuantity();
        }
    }

    private void askBuyMoreProduct(BuyProductDto boughtProduct) {
        if (boughtProduct.getNeedQuantity() < 0) {
            return;
        }

        String answer = InputView.inputBuyMoreProduct(boughtProduct.getName(), boughtProduct.getNeedQuantity());
        InputValidator.validateYesNo(answer);
        if (answer.equals("Y")) {
            boughtProduct.decreaseNeedQuantity();
        }
    }
}
