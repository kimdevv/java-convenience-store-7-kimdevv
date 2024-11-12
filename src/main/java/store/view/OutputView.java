package store.view;

import static store.constant.Constants.*;
import store.dto.BillingDto;
import store.dto.BuyProductDto;
import store.dto.ProductInfoDto;

import java.util.List;

public class OutputView {
    public static void outputWelcomeMessage() {
        System.out.println("안녕하세요. W편의점입니다.");
    }

    public static void outputCurrentInventory(List<ProductInfoDto> allProducts) {
        System.out.println("현재 보유하고 있는 상품입니다.\n");
        for (ProductInfoDto product : allProducts) {
            if (product.promotionName() != null) {
                System.out.printf("- %s %,d원 %s %s \n", product.name(),
                        product.price(), outputQuantity(product.promotionQuantity()), product.promotionName());
            }
            System.out.printf("- %s %,d원 %s \n", product.name(),
                    product.price(), outputQuantity(product.normalQuantity()));
        }
    }

    private static String outputQuantity(int quantity) {
        if (quantity == MINIMUN_QUANTITY_OF_PRODUCT) {
            return "재고 없음";
        }
        return String.format("%,d개", quantity);
    }

    public static void outputGoodBye(List<BuyProductDto> boughtProducts, BillingDto billingResult) {
        outputBoughtProducts(boughtProducts);
        outputBillingResult(billingResult);
    }

    private static void outputBoughtProducts(List<BuyProductDto> boughtProducts) {
        StringBuilder allProducts = new StringBuilder("==============W 편의점================\n상품명\s\s수량\s금액\n");
        StringBuilder freeProducts = new StringBuilder("==============증\s정================\n");
        for (BuyProductDto boughtProduct : boughtProducts) {
            allProducts.append(String.format("%s\s\s%d\s%,d\n", boughtProduct.name(),
                    boughtProduct.quantity(), boughtProduct.quantity() * boughtProduct.unitPrice()));
            if (boughtProduct.freeQuantity() > 0) {
                freeProducts.append(String.format("%s\s\s%d\n", boughtProduct.name(), boughtProduct.freeQuantity()));
            }
        }
        System.out.print(allProducts.append(freeProducts));
    }

    private static void outputBillingResult(BillingDto billingResult) {
        StringBuilder billResult = new StringBuilder("====================================\n");
        billResult.append(String.format("총구매액\s\s%d\s%,d\n", billingResult.totalCount(), billingResult.totalPrice()));
        billResult.append(String.format("행사할인\s\s\s-%,d\n", billingResult.promotionSale()));
        billResult.append(String.format("멤버십할인\s\s\s-%,d\n", billingResult.membershipSale()));
        billResult.append(String.format("내실돈\s\s\s%,d\n",billingResult.payCost()));
        System.out.println(billResult);
    }
}
