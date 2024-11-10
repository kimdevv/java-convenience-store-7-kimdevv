package store.utility;

import store.enumerate.ExceptionEnum;

public class InputValidator {

    public static void validateYesNo(String answer) {
        if (answer.equals("Y") || answer.equals("N")) {
            return;
        }
        ExceptionThrower.throwing(ExceptionEnum.ANSWER_HAVE_TO_YN);
    }
}
