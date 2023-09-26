package 메뉴.VO;

public class Master_VO {
    private int bizNum;
    private String masterPW;
    private String masterStore;
    private String masterName;
    private String StoreNum;

    public Master_VO(int bizNum, String masterPW, String masterStore, String masterName, String storeNum) {
        this.bizNum = bizNum;
        this.masterPW = masterPW;
        this.masterStore = masterStore;
        this.masterName = masterName;
        StoreNum = storeNum;
    }

    public int getBizNum() {
        return bizNum;
    }

    public void setBizNum(int bizNum) {
        this.bizNum = bizNum;
    }

    public String getMasterPW() {
        return masterPW;
    }

    public void setMasterPW(String masterPW) {
        this.masterPW = masterPW;
    }

    public String getMasterStore() {
        return masterStore;
    }

    public void setMasterStore(String masterStore) {
        this.masterStore = masterStore;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getStoreNum() {
        return StoreNum;
    }

    public void setStoreNum(String storeNum) {
        StoreNum = storeNum;
    }
}