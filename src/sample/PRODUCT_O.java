package sample;

public class PRODUCT_O {
    public int PRODNR;
    public String PRODNAME;
    public String PRODTYPEAVAILABLE;
    public int QUANTITY;

    public PRODUCT_O() {
    }

    public PRODUCT_O(int PRODNR, String PRODNAME, String PRODTYPEAVAILABLE, int QUANTITY) {
        this.PRODNR = PRODNR;
        this.PRODNAME = PRODNAME;
        this.PRODTYPEAVAILABLE = PRODTYPEAVAILABLE;
        this.QUANTITY = QUANTITY;
    }

    public int getPRODNR() {
        return PRODNR;
    }

    public void setPRODNR(int PRODNR) {
        this.PRODNR = PRODNR;
    }

    public String getPRODNAME() {
        return PRODNAME;
    }

    public void setPRODNAME(String PRODNAME) {
        this.PRODNAME = PRODNAME;
    }

    public String getPRODTYPEAVAILABLE() {
        return PRODTYPEAVAILABLE;
    }

    public void setPRODTYPEAVAILABLE(String PRODTYPEAVAILABLE) {
        this.PRODTYPEAVAILABLE = PRODTYPEAVAILABLE;
    }

    public int getQUANTITY() {
        return QUANTITY;
    }

    public void setQUANTITY(int QUANTITY) {
        this.QUANTITY = QUANTITY;
    }
}
