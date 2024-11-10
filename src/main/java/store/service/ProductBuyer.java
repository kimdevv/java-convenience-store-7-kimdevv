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
                    return checkIfLackOrNeed(inventory, boughtProduct);
                })
                .collect(Collectors.toList());
    }

    private BuyProductDto checkIfLackOrNeed(Inventory inventory, BuyProductDto boughtProduct) {
        if (boughtProduct.lackQuantity() > MINIMUN_QUANTITY_OF_PRODUCT) {
            boughtProduct = askNoPromotionSale(inventory, boughtProduct);
        }
        if (boughtProduct.needQuantity() > MINIMUN_QUANTITY_OF_PRODUCT) {
            boughtProduct = askBuyMoreProduct(inventory, boughtProduct);
        }
        return boughtProduct;
    }

    private BuyProductDto askNoPromotionSale(Inventory inventory, BuyProductDto boughtProduct) {
        while (true) {
            try {
                String answer = InputView.inputNoPromotionSale(boughtProduct.name(), boughtProduct.lackQuantity());
                InputValidator.validateYesNo(answer);
                return decreaseLackQuantityByAnswer(inventory, boughtProduct, answer);
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private BuyProductDto decreaseLackQuantityByAnswer(Inventory inventory,
                                                       BuyProductDto boughtProduct, String answer) {
        if (BooleanMakerFromYN.make(answer)) {
            return boughtProduct;
        }
        return inventory.decreaseLackQuantity(boughtProduct);
    }

    private BuyProductDto askBuyMoreProduct(Inventory inventory, BuyProductDto boughtProduct) {
        while (true) {
            try {
                String answer = InputView.inputBuyMoreProduct(boughtProduct.name(), boughtProduct.needQuantity());
                InputValidator.validateYesNo(answer);
                return decreaseNeedQuantityByAnswer(inventory, boughtProduct, answer);
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private BuyProductDto decreaseNeedQuantityByAnswer(Inventory inventory,
                                                       BuyProductDto boughtProduct, String answer) {
        if (BooleanMakerFromYN.make(answer)) {
            return inventory.increaseNeedQuantity(boughtProduct);
        }
        return boughtProduct;
    }
}
