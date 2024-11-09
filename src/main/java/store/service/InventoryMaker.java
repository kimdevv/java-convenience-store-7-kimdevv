package store.service;

import store.dto.ParsedProductDto;
import store.model.product.Product;
import store.model.promotion.Promotion;
import store.model.store.Promotions;

import java.util.ArrayList;
import java.util.List;

public class InventoryMaker {
    private Promotions promotions;

    public InventoryMaker(Promotions promotions) {
        this.promotions = promotions;
    }

    public List<Product> setInventoryFromParsedString(List<ParsedProductDto> parsedProducts) {
        List<Product> products = new ArrayList<>();
        for (ParsedProductDto parsedProduct : parsedProducts) {
            String productName = parsedProduct.name();
            int price = parsedProduct.price();
            int quantity = parsedProduct.quantity();
            String promotionName = parsedProduct.promotion();
            
            products.add(generateProductFromParsedData(products, productName, price, quantity, promotionName));
        }
        return products;
    }
    
    private Product generateProductFromParsedData(List<Product> products, String productName, int price, int quantity, String promotionName) {
        Product product = findProductByNameOrNew(products, productName, price);

        Promotion promotion = promotions.findPromotionByPromotionName(promotionName);
        if (product.isPromotionNull()) {
            product.setPromotion(promotion);
        }
        setQuantityWithPromotion(product, quantity, promotion);

        return product;
    }
    
    private Product findProductByNameOrNew(List<Product> products, String productName, int price) {
        Product existProductWithName = products.stream()
                .filter(product -> product.getName().equals(productName))
                .findFirst()
                .orElse(null);

        if (existProductWithName == null) {
            return new Product(productName, price);
        }
        products.remove(existProductWithName);
        return existProductWithName;
    }
    
    private void setQuantityWithPromotion(Product product, int quantity, Promotion promotion) {
        if (promotion != null) {
            product.setPromotionQuantity(quantity);
            return;
        }
        product.setNormalQuantity(quantity);
    }
}
