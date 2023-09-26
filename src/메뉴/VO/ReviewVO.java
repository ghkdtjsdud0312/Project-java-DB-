
package 메뉴.VO;

public class ReviewVO {
    int REVIEW_NUM;
    int STORE_NUM;
    String MENU_NAME;
    String USER_ID;
    int SCORE;
    String WRITE_REVIEW;

    public ReviewVO(int REVIEW_NUM, int STORE_NUM, String MENU_NAME, int SCORE, String WRITE_REVIEW , String USER_ID) {
        this.REVIEW_NUM = REVIEW_NUM;
        this.STORE_NUM = STORE_NUM;
        this.MENU_NAME = MENU_NAME;
        this.USER_ID = USER_ID;
        this.SCORE = SCORE;
        this.WRITE_REVIEW = WRITE_REVIEW;
    }

    public int getREVIEW_NUM() {
        return REVIEW_NUM;
    }

    public void setREVIEW_NUM(int REVIEW_NUM) {
        this.REVIEW_NUM = REVIEW_NUM;
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

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public int getSCORE() {
        return SCORE;
    }

    public void setSCORE(int SCORE) {
        this.SCORE = SCORE;
    }

    public String getWRITE_REVIEW() {
        return WRITE_REVIEW;
    }

    public void setWRITE_REVIEW(String WRITE_REVIEW) {
        this.WRITE_REVIEW = WRITE_REVIEW;
    }
}