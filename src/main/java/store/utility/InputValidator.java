package store.utility;

import static store.constant.Constants.*;
import store.enumerate.ExceptionEnum;

public class InputValidator {

    public static void validateYesNo(String answer) {
        if (answer.equals(YES) || answer.equals(NO)) {
            return;
        }
        throw new IllegalArgumentException(ExceptionEnum.ANSWER_HAVE_TO_YN.getMessage());
    }
}
