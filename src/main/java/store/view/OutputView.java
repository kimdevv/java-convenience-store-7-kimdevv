package store.view;

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
}
