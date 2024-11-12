package store.utility;

import store.dto.BuyProductParseDto;
import store.enumerate.ExceptionEnum;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static store.constant.Constants.*;

public class BuyProductParser {
    private static final Pattern INPUT_FORMAT = Pattern.compile("\\[[가-힣a-zA-Z]+-\\d+\\]");

    public static List<BuyProductParseDto> parse(String rawProductInput) {
        return List.of(rawProductInput.split(BUY_PRODUCT_INPUT_DELIMITER))
                .stream()
                .map(rawInput -> {
                    validateFormat(rawInput);
                    String[] rawProductAndQuantity = parseNameAndQuantityFromRawInput(rawInput);
                    String rawProduct = rawProductAndQuantity[0];
                    int quantity = Integer.parseInt(rawProductAndQuantity[1]);
                    return new BuyProductParseDto(rawProduct, quantity);
                })
                .collect(Collectors.toList());
    }

    private static void validateFormat(String rawProductInput) {
        if (!INPUT_FORMAT.matcher(rawProductInput).matches()) {
            throw new IllegalArgumentException(ExceptionEnum.INVALID_BUY_INPUT_FORMAT.getMessage());
        }
    }

    private static String[] parseNameAndQuantityFromRawInput(String rawInput) {
        return rawInput
                .replace(BUY_PRODUCT_INPUT_STARTER, "")
                .replace(BUY_PRODUCT_INPUT_ENDER, "")
                .split(BUY_PRODUCT_NAME_AND_QUANTITY_DELIMITER);
    }
}
