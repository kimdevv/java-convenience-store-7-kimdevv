package store.enumerate;

public enum ExceptionEnum {
    FILE_NOT_FOUND("파일을 찾을 수 없습니다."),
    READ_LINE_ERROR("파일을 읽는 데 실패하였습니다."),
    CANNOT_FIND_PRODUCT("존재하지 않는 상품입니다. 다시 입력해 주세요."),
    TOO_MANY_COUNT("재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요."),
    INVALID_BUY_INPUT_FORMAT("올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요."),
    ANSWER_HAVE_TO_YN("잘못된 입력입니다. 다시 입력해 주세요."),
    NO_DECREASABLE_COUNT("추가하거나 감소할 수 있는 수량이 없습니다. 잘못된 요청입니다.");

    private final String message;

    ExceptionEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return "[ERROR]" + message;
    }
}