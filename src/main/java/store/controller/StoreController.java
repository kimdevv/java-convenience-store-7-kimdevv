package store.controller;

import store.dto.*;
import store.model.product.Product;
import store.model.store.Inventory;
import store.model.store.Promotions;
import store.service.InventoryMaker;
import store.service.ProductBuyer;
import store.utility.BooleanMakerFromYN;
import store.utility.FileReader.InventoryFileReader;
import store.utility.FileReader.PromotionFileReader;
import store.utility.InputValidator;
import store.utility.PriceCalculator;
import store.view.InputView;
import store.view.OutputView;

import java.util.List;

public class StoreController {
    public void run() {
        Inventory inventory = setFromFile();
        openConvenienceStore(inventory);
    }

    private void openConvenienceStore(Inventory inventory) {
        outputStoreGreeting(inventory.getAllProductsInfomation());

        List<BuyProductDto> boughtProducts = getBoughtProducts(inventory);
        BillingDto billingResult = calculatePrices(boughtProducts);

        OutputView.outputGoodBye(boughtProducts, billingResult);
        askRebuy(inventory);
    }

    private Inventory setFromFile() {
        Promotions promotions = new Promotions();
        promotions.setPromotionsFromParsedString(PromotionFileReader.inputPromotionsFromFile());

        List<ParsedProductDto> parsedProducts = InventoryFileReader.inputInventoryFromFile();
        InventoryMaker inventoryMaker = new InventoryMaker(promotions);
        List<Product> allProducts = inventoryMaker.setInventoryFromParsedString(parsedProducts);
        return new Inventory(allProducts);
    }

    private void outputStoreGreeting(List<ProductInfoDto> allProducts) {
        OutputView.outputWelcomeMessage();
        OutputView.outputCurrentInventory(allProducts);
    }

    private List<BuyProductDto> getBoughtProducts(Inventory inventory) {
        while (true) {
            try {
                ProductBuyer productBuyer = new ProductBuyer();
                return productBuyer.buyProduct(inventory, InputView.inputBuyProducts());
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private BillingDto calculatePrices(List<BuyProductDto> boughtProducts) {
        boolean isMembership = askMembershipSale();
        return PriceCalculator.calculate(boughtProducts, isMembership);
    }

    private boolean askMembershipSale() {
        while (true) {
            try {
                String rawAnswer = InputView.inputMembershipSale();
                InputValidator.validateYesNo(rawAnswer);
                return BooleanMakerFromYN.make(rawAnswer);
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private void askRebuy(Inventory inventory) {
        while (true) {
            try {
                String rawAnser = InputView.inputRebuy();
                InputValidator.validateYesNo(rawAnser);
                restartStoreWithAnswer(rawAnser, inventory);
                return;
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private void restartStoreWithAnswer(String answer, Inventory inventory) {
        if (BooleanMakerFromYN.make(answer)) {
            openConvenienceStore(inventory);
        }
    }
}
