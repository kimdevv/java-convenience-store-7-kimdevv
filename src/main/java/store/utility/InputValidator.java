package store.utility;

import static store.constant.Constants.*;
import store.enumerate.ExceptionEnum;

public class InputValidator {

    public static void validateYesNo(String answer) {
        if (answer.equals(YES) || answer.equals(NO)) {
            return;
        }
        throw new IllegalArgumentException(ExceptionEnum.WRONG_INPUT.getMessage());
    }

    public static void validateQuantityUnderMinimumCount(int quantity) {
        if (quantity <= MINIMUN_QUANTITY_OF_PRODUCT) {
            throw new IllegalArgumentException(ExceptionEnum.WRONG_INPUT.getMessage());
        }
    }
}
