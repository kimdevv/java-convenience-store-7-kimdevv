package store.dto;

import static store.constant.Constants.*;
import store.enumerate.ExceptionEnum;

public record BuyProductDto(String name, int quantity, int unitPrice,
                            int freeQuantity, int lackQuantity, int needQuantity) {
}
