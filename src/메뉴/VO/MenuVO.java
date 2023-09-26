package 메뉴.VO;

public class MenuVO {
    private String CATEGORY_INFO;
    private int STORE_NUM;
    private String MENU_NAME;
    private int PRICE;

    public MenuVO(String CATEGORY_INFO) {
        this.CATEGORY_INFO = CATEGORY_INFO;
    }

    public MenuVO(String CATEGORY_INFO, int STORE_NUM) {
        this.CATEGORY_INFO = CATEGORY_INFO;
        this.STORE_NUM = STORE_NUM;
    }

    public MenuVO(String CATEGORY_INFO, int STORE_NUM, String MENU_NAME) {
        this.CATEGORY_INFO = CATEGORY_INFO;
        this.STORE_NUM = STORE_NUM;
        this.MENU_NAME = MENU_NAME;
    }

    public MenuVO(String CATEGORY_INFO, int STORE_NUM, String MENU_NAME, int PRICE) {
        this.CATEGORY_INFO = CATEGORY_INFO;
        this.STORE_NUM = STORE_NUM;
        this.MENU_NAME = MENU_NAME;
        this.PRICE = PRICE;
    }

    public String getCATEGORY_INFO() {
        return CATEGORY_INFO;
    }

    public void setCATEGORY_INFO(String CATEGORY_INFO) {
        this.CATEGORY_INFO = CATEGORY_INFO;
    }

    public int getSTORE_NUM() {
        return STORE_NUM;
    }

    public void setSTORE_NUM(int STORE_NUM) {
        this.STORE_NUM = STORE_NUM;
    }

    public String getMENU_NAME() {
        return MENU_NAME;
    }

    public void setMENU_NAME(String MENU_NAME) {
        this.MENU_NAME = MENU_NAME;
    }

    public int getPRICE() {
        return PRICE;
    }

    public void setPRICE(int PRICE) {
        this.PRICE = PRICE;
    }
}