package 메뉴.Exception;

public class UsedIdException extends IllegalArgumentException {
    static final String YELLOW = "\u001B[33m";
    static final String RESET = "\u001B[0m";
    public UsedIdException() {
        super(YELLOW + "이미 사용 중인 아이디입니다. 다른 아이디를 입력하세요." + RESET);
    }

}