package store.utility;

import store.enumerate.ExceptionEnum;

public class InputValidator {

    public static void validateYesNo(String answer) {
        if (answer.equals("Y") || answer.equals("N")) {
            return;
        }
        throw new IllegalArgumentException(ExceptionEnum.ANSWER_HAVE_TO_YN.getMessage());
    }
}
