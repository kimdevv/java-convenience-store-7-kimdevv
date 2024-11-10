package store.controller;

import store.dto.*;
import store.model.product.Product;
import store.model.store.Inventory;
import store.model.store.Promotions;
import store.service.InventoryMaker;
import store.utility.BuyProductParser;
import store.utility.FileReader.InventoryFileReader;
import store.utility.FileReader.PromotionFileReader;
import store.utility.InputValidator;
import store.utility.PriceCalculator;
import store.view.InputView;
import store.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StoreController {
    public void openConvenienceStore() {
        Promotions promotions = setPromotionFromFile();
        Inventory inventory = setInventoryFromFile(promotions);
        outputStoreGreeting(inventory.getAllProductsInfomation());

        List<BuyProductDto> boughtProducts = buyProduct(inventory);
        BillingDto billingResult = calculatePrices(boughtProducts);

        OutputView.outputGoodBye(boughtProducts, billingResult);
        askRebuy();
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
        while (true) {
            try {
                List<BuyProductParseDto> rawBuyProducts = BuyProductParser.parse(InputView.inputBuyProducts());
                return collectBoughtProduct(inventory, rawBuyProducts);
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private List<BuyProductDto> collectBoughtProduct(Inventory inventory, List<BuyProductParseDto> rawBuyProducts) {
        return rawBuyProducts.stream()
                .map(rawBuyProduct -> {
                    BuyProductDto boughtProduct = inventory.buy(rawBuyProduct.name(), rawBuyProduct.quantity());
                    checkIfLackOrNeed(boughtProduct);
                    return boughtProduct;
                })
                .collect(Collectors.toList());
    }

    private void checkIfLackOrNeed(BuyProductDto boughtProduct) {
        if (boughtProduct.getLackQuantity() > 0) {
            askNoPromotionSale(boughtProduct);
        }
        if (boughtProduct.getNeedQuantity() > 0) {
            askBuyMoreProduct(boughtProduct);
        }
    }

    private void askNoPromotionSale(BuyProductDto boughtProduct) {
        while (true) {
            try {
                String answer = InputView.inputNoPromotionSale(boughtProduct.getName(), boughtProduct.getLackQuantity());
                InputValidator.validateYesNo(answer);
                decreaseLackQuantityByAnswer(boughtProduct, answer);
                return;
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private void decreaseLackQuantityByAnswer(BuyProductDto boughtProduct, String answer) {
        if (answer.equals("N")) {
            boughtProduct.decreaseLackQuantity();
        }
    }

    private void askBuyMoreProduct(BuyProductDto boughtProduct) {
        while (true) {
            try {
                String answer = InputView.inputBuyMoreProduct(boughtProduct.getName(), boughtProduct.getNeedQuantity());
                InputValidator.validateYesNo(answer);
                decreaseNeedQuantityByAnswer(boughtProduct, answer);
                return;
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private void decreaseNeedQuantityByAnswer(BuyProductDto boughtProduct, String answer) {
        if (answer.equals("Y")) {
            boughtProduct.decreaseNeedQuantity();
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
                return makeBooleanFromAnswer(rawAnswer);
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private boolean makeBooleanFromAnswer(String answer) {
        if (answer.equals("Y")) {
            return true;
        }
        return false;
    }

    private void askRebuy() {
        while (true) {
            try {
                String rawAnser = InputView.inputRebuy();
                InputValidator.validateYesNo(rawAnser);
                restartStoreWithAnswer(rawAnser);
                return;
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private void restartStoreWithAnswer(String answer) {
        if (answer.equals("Y")) {
            openConvenienceStore();
        }
    }
}
