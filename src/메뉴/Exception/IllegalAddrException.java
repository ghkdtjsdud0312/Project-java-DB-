package 메뉴.Exception;

public class IllegalAddrException extends IllegalArgumentException {

    static final String YELLOW = "\u001B[33m";
    static final String RESET = "\u001B[0m";
    public IllegalAddrException(String message) {
        super(YELLOW + message + RESET);
    }
}