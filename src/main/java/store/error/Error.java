package store.error;

public interface Error {
    String PREFIX = "[ERROR] ";
    String SUFFIX = " 다시 입력해 주세요.";

    String error();
}
