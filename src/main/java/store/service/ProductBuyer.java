package store.service;

import store.dto.BuyProductDto;
import store.dto.BuyProductParseDto;
import store.model.store.Inventory;
import store.utility.BooleanMakerFromYN;
import store.utility.BuyProductParser;
import store.utility.InputValidator;
import store.view.InputView;

import java.util.List;
import java.util.stream.Collectors;

import static store.constant.Constants.*;

public class ProductBuyer {
    public List<BuyProductDto> buyProduct(Inventory inventory) {
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
        if (boughtProduct.getLackQuantity() > MINIMUN_QUANTITY_OF_PRODUCT) {
            askNoPromotionSale(boughtProduct);
        }
        if (boughtProduct.getNeedQuantity() > MINIMUN_QUANTITY_OF_PRODUCT) {
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
        if (BooleanMakerFromYN.make(answer)) {
            return;
        }
        boughtProduct.decreaseLackQuantity();
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
        if (BooleanMakerFromYN.make(answer)) {
            boughtProduct.increaseNeedQuantity();
        }
    }
}
