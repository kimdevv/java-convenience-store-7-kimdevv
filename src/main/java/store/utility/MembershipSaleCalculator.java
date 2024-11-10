package store.utility;

import static store.constant.Constants.*;

public class MembershipSaleCalculator {
    public static int calculate(int originalTotalPrice) {
        int membershipSalePrice = originalTotalPrice * MEMBERSHIP_SALE_RATE / 100;
        if (membershipSalePrice > MAXIMUM_MEMBERSHIP_SALE || membershipSalePrice < OVERFLOW_THRESHOLD) {
            return MAXIMUM_MEMBERSHIP_SALE;
        }
        return membershipSalePrice;
    }
}
