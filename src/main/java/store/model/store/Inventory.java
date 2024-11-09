package store.model.store;

import store.dto.BuyProductDto;
import store.enumerate.ExceptionEnum;
import store.model.product.Product;
import store.utility.ExceptionThrower;

import java.util.List;

public class Inventory {
    private List<Product> products;

    public Inventory(List<Product> allProducts) {
        this.products = allProducts;
    }

    private BuyProductDto buy(String productName, int quantity) {
        Product product = findProductByName(productName);
        validateProductIsNull(product);

        return makeBuyDto(product, quantity);
    }

    private Product findProductByName(String productName) {
        return this.products.stream()
                .filter(product -> product.getName().equals(productName))
                .findFirst()
                .orElseGet(null);
    }

    private void validateProductIsNull(Product product) {
        if (product == null) {
            ExceptionThrower.throwing(ExceptionEnum.CANNOT_FIND_PRODUCT);
        }
    }

    private BuyProductDto makeBuyDto(Product product, int quantity) {
        String name = product.getName();
        int unitPrice = product.getPrice();

    }
}
