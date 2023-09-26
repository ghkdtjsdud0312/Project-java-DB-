package 메뉴.VO;
import java.sql.Date;

public class OrderVO {
    private int ORDER_NUM;
    private Date DATE_NUM;
    private int STORE_NUM;
    private String MENU_NAME;
    private int AMOUNT;
    private String DILIVERY_OPTION;
    private String PAYMENT_OPTION;
    private int TOTAL_PAYMENT;
    private int PRICE;
    private int TOTAL;
    private String CATEGORY_INFO;

    public OrderVO(){}

    public OrderVO(int ORDER_NUM, Date DATE_NUM, int STORE_NUM, String MENU_NAME, int AMOUNT, int PRICE, int TOTAL_PAYMENT) {
        this.ORDER_NUM = ORDER_NUM;
        this.DATE_NUM = DATE_NUM;
        this.STORE_NUM = STORE_NUM;
        this.MENU_NAME = MENU_NAME;
        this.AMOUNT = AMOUNT;
        this.TOTAL_PAYMENT = TOTAL_PAYMENT;
        this.PRICE = PRICE;
    }

    public OrderVO(int ORDER_NUM, int STORE_NUM, String MENU_NAME, int AMOUNT, String DILIVERY_OPTION, String PAYMENT_OPTION) {
        this.ORDER_NUM = ORDER_NUM;
        this.STORE_NUM = STORE_NUM;
        this.MENU_NAME = MENU_NAME;
        this.AMOUNT = AMOUNT;
        this.DILIVERY_OPTION = DILIVERY_OPTION;
        this.PAYMENT_OPTION = PAYMENT_OPTION;
        this.PRICE = PRICE;
        this.CATEGORY_INFO = CATEGORY_INFO;
    }

    public OrderVO(String CATEGORY_INFO, int STORE_NUM, String MENU_NAME, int TOTAL) {
        this.CATEGORY_INFO = CATEGORY_INFO;
        this.STORE_NUM = STORE_NUM;
        this.MENU_NAME = MENU_NAME;
        this.TOTAL = TOTAL;
    }


    public OrderVO(int ORDER_NUM, int STORE_NUM, String MENU_NAME, int AMOUNT, int PRICE, int TOTAL_PAYMENT) {
        this.ORDER_NUM = ORDER_NUM;
        this.STORE_NUM = STORE_NUM;
        this.MENU_NAME = MENU_NAME;
        this.AMOUNT = AMOUNT;
        this.TOTAL_PAYMENT = TOTAL_PAYMENT;
        this.PRICE = PRICE;
    }

    public int getPRICE() {
        return PRICE;
    }

    public void setPRICE(int PRICE) {
        this.PRICE = PRICE;
    }

    public OrderVO(int STORE_NUM, String MENU_NAME, int AMOUNT, String DILIVERY_OPTION, String PAYMENT_OPTION) {
        this.STORE_NUM = STORE_NUM;
        this.MENU_NAME = MENU_NAME;
        this.AMOUNT = AMOUNT;
        this.DILIVERY_OPTION = DILIVERY_OPTION;
        this.PAYMENT_OPTION = PAYMENT_OPTION;
    }

    public OrderVO(int ORDER_NUM) {
        this.ORDER_NUM = ORDER_NUM;
    }

    public OrderVO(int ORDER_NUM, Date DATE_NUM) {
        this.ORDER_NUM = ORDER_NUM;
        this.DATE_NUM = DATE_NUM;
    }

    public OrderVO(int ORDER_NUM, Date DATE_NUM, int STORE_NUM) {
        this.ORDER_NUM = ORDER_NUM;
        this.DATE_NUM = DATE_NUM;
        this.STORE_NUM = STORE_NUM;
    }

    public OrderVO(int ORDER_NUM, Date DATE_NUM, int STORE_NUM, String MENU_NAME) {
        this.ORDER_NUM = ORDER_NUM;
        this.DATE_NUM = DATE_NUM;
        this.STORE_NUM = STORE_NUM;
        this.MENU_NAME = MENU_NAME;
    }

    public OrderVO(int ORDER_NUM, Date DATE_NUM, int STORE_NUM, String MENU_NAME, int AMOUNT) {
        this.ORDER_NUM = ORDER_NUM;
        this.DATE_NUM = DATE_NUM;
        this.STORE_NUM = STORE_NUM;
        this.MENU_NAME = MENU_NAME;
        this.AMOUNT = AMOUNT;
    }

    public OrderVO(int ORDER_NUM, Date DATE_NUM, int STORE_NUM, String MENU_NAME, int AMOUNT, String DILIVERY_OPTION) {
        this.ORDER_NUM = ORDER_NUM;
        this.DATE_NUM = DATE_NUM;
        this.STORE_NUM = STORE_NUM;
        this.MENU_NAME = MENU_NAME;
        this.AMOUNT = AMOUNT;
        this.DILIVERY_OPTION = DILIVERY_OPTION;
    }

    public OrderVO(int ORDER_NUM, Date DATE_NUM, int STORE_NUM, String MENU_NAME, int AMOUNT, String DILIVERY_OPTION, String PAYMENT_OPTION) {
        this.ORDER_NUM = ORDER_NUM;
        this.DATE_NUM = DATE_NUM;
        this.STORE_NUM = STORE_NUM;
        this.MENU_NAME = MENU_NAME;
        this.AMOUNT = AMOUNT;
        this.DILIVERY_OPTION = DILIVERY_OPTION;
        this.PAYMENT_OPTION = PAYMENT_OPTION;
    }

    public OrderVO(int ORDER_NUM, Date DATE_NUM, int STORE_NUM, String MENU_NAME, int AMOUNT, String DILIVERY_OPTION, String PAYMENT_OPTION, int TOTAL_PAYMENT) {
        this.ORDER_NUM = ORDER_NUM;
        this.DATE_NUM = DATE_NUM;
        this.STORE_NUM = STORE_NUM;
        this.MENU_NAME = MENU_NAME;
        this.AMOUNT = AMOUNT;
        this.DILIVERY_OPTION = DILIVERY_OPTION;
        this.PAYMENT_OPTION = PAYMENT_OPTION;
        this.TOTAL_PAYMENT = TOTAL_PAYMENT;
    }

    public OrderVO(String storeNum, String menuName, String amount, String diliveryOption, String paymentOption) {
    }

    public int getORDER_NUM() {
        return ORDER_NUM;
    }

    public void setORDER_NUM(int ORDER_NUM) {
        this.ORDER_NUM = ORDER_NUM;
    }

    public Date getDATE_NUM() {
        return DATE_NUM;
    }

    public void setDATE_NUM(Date DATE_NUM) {
        this.DATE_NUM = DATE_NUM;
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

    public int getAMOUNT() {
        return AMOUNT;
    }

    public void setAMOUNT(int AMOUNT) {
        this.AMOUNT = AMOUNT;
    }

    public String getDILIVERY_OPTION() {
        return DILIVERY_OPTION;
    }

    public void setDILIVERY_OPTION(String DILIVERY_OPTION) {
        this.DILIVERY_OPTION = DILIVERY_OPTION;
    }

    public String getPAYMENT_OPTION() {
        return PAYMENT_OPTION;
    }

    public void setPAYMENT_OPTION(String PAYMENT_OPTION) {
        this.PAYMENT_OPTION = PAYMENT_OPTION;
    }

    public int getTOTAL_PAYMENT() {
        return TOTAL_PAYMENT;
    }

    public void setTOTAL_PAYMENT(int TOTAL_PAYMENT) {
        this.TOTAL_PAYMENT = TOTAL_PAYMENT;
    }

    public int getTOTAL() {
        return TOTAL;
    }

    public void setTOTAL(int TOTAL) {
        this.TOTAL = TOTAL;
    }

    public String getCATEGORY_INFO() {
        return CATEGORY_INFO;
    }

    public void setCATEGORY_INFO(String CATEGORY_INFO) {
        this.CATEGORY_INFO = CATEGORY_INFO;
    }
}