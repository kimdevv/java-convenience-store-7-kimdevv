package store.view;

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
                System.out.printf("- %s %,d원 %d개 %s \n"
                        , product.name(), product.price(), product.promotionQuantity(), product.promotionName());
            }
            System.out.printf("- %s %,d원 %d개 \n"
                    , product.name(), product.price(), product.normalQuantity());
        }
    }

    public static void outputGoodBye(List<BuyProductDto> boughtProducts, BillingDto billingResult) {
        outputBoughtProducts(boughtProducts);
        outputBillingResult(billingResult);
    }

    private static void outputBoughtProducts(List<BuyProductDto> boughtProducts) {
        StringBuilder allProducts = new StringBuilder("==============W 편의점================\n상품명\t\t수량\t금액\n");
        StringBuilder freeProducts = new StringBuilder("==============증\t정================\n");
        for (BuyProductDto boughtProduct : boughtProducts) {
            allProducts.append(String.format("%s/t/t%d/t%,d\n", boughtProduct.getName(),
                    boughtProduct.getQuantity(), boughtProduct.getQuantity() * boughtProduct.getUnitPrice()));
            if (boughtProduct.getFreeQuantity() > 0) {
                freeProducts.append(String.format("%s\t\t%d", boughtProduct.getName(), boughtProduct.getFreeQuantity()));
            }
        }
        System.out.println(allProducts.append(freeProducts));
    }

    private static void outputBillingResult(BillingDto billingResult) {
        StringBuilder billResult = new StringBuilder("====================================");
        billResult.append(String.format("총구매액\t\t%d\t%,d", billingResult.totalCount(), billingResult.totalPrice()));
        billResult.append(String.format("행사할인\t\t\t-%d", billingResult.promotionSale()));
        billResult.append(String.format("멤버십할인\t\t\t-%d", billingResult.membershipSale()));
        billResult.append(String.format("내실돈\t\t\t %d",billingResult.payCost()));
        System.out.println(billResult);
    }
}
