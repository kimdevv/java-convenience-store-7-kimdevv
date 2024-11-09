package store.utility;

public class MembershipSaleCalculator {
    public static int calculate(int originalTotalPrice) {
        int membershipSalePrice = originalTotalPrice * 30 / 100;
        if (membershipSalePrice > 8000) {
            return 8000;
        }
        return membershipSalePrice;
    }
}
