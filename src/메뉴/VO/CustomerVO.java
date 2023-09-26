package 메뉴.VO;

public class CustomerVO {
    private String USER_ID;
    private String USER_PW;

    public CustomerVO(String USER_ID, String USER_PW, String USER_NAME, String GENDER, String PHONE, String ADDR, String BIRTH, String EMAIL, String MONTHFEE) {
        this.USER_ID = USER_ID;
        this.USER_PW = USER_PW;
        this.USER_NAME = USER_NAME;
        this.GENDER = GENDER;
        this.PHONE = PHONE;
        this.ADDR = ADDR;
        this.BIRTH = BIRTH;
        this.EMAIL = EMAIL;
        this.MONTHFEE = MONTHFEE;
    }

    private String USER_NAME;
    private String GENDER;
    private String PHONE;
    private String ADDR;
    private String BIRTH;
    private String EMAIL;
    private String MONTHFEE;

    public CustomerVO() { }
    public CustomerVO(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getUSER_PW() {
        return USER_PW;
    }

    public void setUSER_PW(String USER_PW) {
        this.USER_PW = USER_PW;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public String getGENDER() {
        return GENDER;
    }

    public void setGENDER(String GENDER) {
        this.GENDER = GENDER;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    public String getADDR() {
        return ADDR;
    }

    public void setADDR(String ADDR) {
        this.ADDR = ADDR;
    }

    public String getBIRTH() {
        return BIRTH;
    }

    public void setBIRTH(String BIRTH) {
        this.BIRTH = BIRTH;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getMONTHFEE() {
        return MONTHFEE;
    }

    public void setMONTHFEE(String MONTHFEE) {
        this.MONTHFEE = MONTHFEE;
    }
}