package store.enumerate;

public enum ExceptionEnum {
    FILE_NOT_FOUND("파일을 찾을 수 없습니다."),
    READ_LINE_ERROR("파일을 읽는 데 실패하였습니다."),
    CANNOT_FIND_PRODUCT("존재하지 않는 상품명입니다."),
    TOO_MANY_COUNT("구입 가능한 개수를 초과하였습니다."),
    INVALID_BUY_INPUT_FORMAT("입력 양식이 잘못되었습니다.");

    private final String message;

    ExceptionEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return "[ERROR]" + message;
    }
}