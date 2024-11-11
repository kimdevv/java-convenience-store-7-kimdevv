package store.model.store;

import static store.constant.Constants.*;
import store.dto.BuyProductDto;
import store.dto.DecreasePromotionQuantityDto;
import store.dto.ProductInfoDto;
import store.enumerate.ExceptionEnum;
import store.model.product.Product;

import java.util.List;
import java.util.stream.Collectors;

public class Inventory {
    private List<Product> products;

    public Inventory(List<Product> allProducts) {
        this.products = allProducts;
    }

    public BuyProductDto buy(String productName, int quantity) {
        Product product = findProductByName(productName);
        validateExistProductName(product);

        return makeBuyDto(product, quantity);
    }

    private Product findProductByName(String productName) {
        return this.products.stream()
                .filter(product -> product.getName().equals(productName))
                .findFirst()
                .orElse(null);
    }

    private void validateExistProductName(Product product) {
        if (product == null) {
            throw new IllegalArgumentException(ExceptionEnum.CANNOT_FIND_PRODUCT.getMessage());
        }
    }

    private BuyProductDto makeBuyDto(Product product, int quantity) {
        String name = product.getName();
        int unitPrice = product.getPrice();
        DecreasePromotionQuantityDto promotionQuantityDto = product.decreaseBoughtQuantity(quantity);
        int freeQuantity = promotionQuantityDto.freeQuantity();
        int lackQuantity = promotionQuantityDto.lackQuantity();
        int needQuantity = promotionQuantityDto.needQuantity();
        return new BuyProductDto(name, quantity, unitPrice, freeQuantity, lackQuantity, needQuantity);
    }

    public List<ProductInfoDto> getAllProductsInfomation() {
        return this.products.stream()
                .map(product -> product.getProductInformation())
                .collect(Collectors.toList());
    }

    public BuyProductDto decreaseLackQuantity(BuyProductDto buyProductDto) {
        String name = buyProductDto.name();
        int newQuantity = buyProductDto.quantity() - buyProductDto.lackQuantity();
        int unitPrice = buyProductDto.unitPrice();
        int freeQuantity = buyProductDto.freeQuantity();
        int newLackQuantity = MINIMUN_QUANTITY_OF_PRODUCT;
        int needQuantity = buyProductDto.needQuantity();
        return new BuyProductDto(name, newQuantity, unitPrice, freeQuantity, newLackQuantity, needQuantity);
    }

    public BuyProductDto increaseNeedQuantity(BuyProductDto buyProductDto) {
        String name = buyProductDto.name();
        int newQuantity = buyProductDto.quantity() + buyProductDto.needQuantity();
        int unitPrice = buyProductDto.unitPrice();
        int newFreeQuantity = buyProductDto.freeQuantity() + buyProductDto.needQuantity();
        int lackQuantity = buyProductDto.lackQuantity();
        int newNeedQuantity = MINIMUN_QUANTITY_OF_PRODUCT;
        return new BuyProductDto(name, newQuantity, unitPrice, newFreeQuantity, lackQuantity, newNeedQuantity);
    }
}
