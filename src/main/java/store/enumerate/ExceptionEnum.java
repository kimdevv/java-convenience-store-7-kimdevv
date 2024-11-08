package store.enumerate;

public enum ExceptionEnum {
    FILE_NOT_FOUND("파일을 찾을 수 없습니다."),
    READ_LINE_ERROR("파일을 읽는 데 실패하였습니다.");

    private final String message;

    ExceptionEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return "[ERROR]" + message;
    }
}