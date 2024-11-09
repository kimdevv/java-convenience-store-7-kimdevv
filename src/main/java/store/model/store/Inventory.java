package store.model.store;

import store.model.product.Product;

import java.util.List;

public class Inventory {
    private List<Product> products;

    public Inventory(List<Product> allProducts) {
        this.products = allProducts;
    }
}
