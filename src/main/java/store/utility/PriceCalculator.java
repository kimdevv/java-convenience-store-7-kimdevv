package store.utility;

import store.dto.BillingDto;
import store.dto.BuyProductDto;

import java.util.List;

public class PriceCalculator {
    public static BillingDto calculate(List<BuyProductDto> boughtProducts, boolean isMembership) {
        int totalCount = calculateTotalCount(boughtProducts);

        int[] totalPriceAndPromotionSale = calculateTotalPriceAndPromotionSale(boughtProducts);
        int totalPrice = totalPriceAndPromotionSale[0];
        int promotionSale = totalPriceAndPromotionSale[1];

        int membershipSale = calculateMembershipSale(totalPrice, promotionSale, isMembership);
        int payCost = totalPrice - promotionSale - membershipSale;
        return new BillingDto(totalCount, totalPrice, promotionSale, membershipSale, payCost);
    }

    private static int calculateTotalCount(List<BuyProductDto> boughtProducts) {
        int totalCount = 0;
        for (BuyProductDto boughtProduct : boughtProducts) {
            totalCount += boughtProduct.getQuantity();
        }
        return totalCount;
    }

    private static int[] calculateTotalPriceAndPromotionSale(List<BuyProductDto> boughtProducts) {
        int totalPrice = 0;
        int promotionSale = 0;
        for (BuyProductDto boughtProduct : boughtProducts) {
            int unitPrice = boughtProduct.getUnitPrice();
            totalPrice += boughtProduct.getQuantity() * unitPrice;
            promotionSale += boughtProduct.getFreeQuantity() * unitPrice;
        }
        return new int[] {totalPrice, promotionSale};
    }

    private static int calculateMembershipSale(int totalPrice, int promotionSale, boolean isMembership) {
        if (isMembership) {
            return MembershipSaleCalculator.calculate(totalPrice - promotionSale);
        }
        return 0;
    }
}
