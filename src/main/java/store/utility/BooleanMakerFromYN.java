package store.utility;

import store.enumerate.ExceptionEnum;

import static store.constant.Constants.*;

public class BooleanMakerFromYN {
    public static boolean make(String YN) {
        if (YN.equals(YES)) {
            return true;
        }
        if (YN.equals(NO)) {
            return false;
        }
        throw new IllegalArgumentException(ExceptionEnum.WRONG_INPUT.getMessage());
    }
}
