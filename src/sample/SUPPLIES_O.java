package sample;

public class SUPPLIES_O {
    public int SUPNR;
    public int PRODNR;
    public double PURCHASE_PRICE;
    public String DELIV_PERIOD;

    public SUPPLIES_O() {

    }

    public SUPPLIES_O(int SUPNR, int PRODNR, double PURCHASE_PRICE, String DELIV_PERIOD) {
        this.SUPNR = SUPNR;
        this.PRODNR = PRODNR;
        this.PURCHASE_PRICE = PURCHASE_PRICE;
        this.DELIV_PERIOD = DELIV_PERIOD;
    }

    public int getSUPNR() {
        return SUPNR;
    }

    public void setSUPNR(int SUPNR) {
        this.SUPNR = SUPNR;
    }

    public int getPRODNR() {
        return PRODNR;
    }

    public void setPRODNR(int PRODNR) {
        this.PRODNR = PRODNR;
    }

    public double getPURCHASE_PRICE() {
        return PURCHASE_PRICE;
    }

    public void setPURCHASE_PRICE(double PURCHASE_PRICE) {
        this.PURCHASE_PRICE = PURCHASE_PRICE;
    }

    public String getDELIV_PERIOD() {
        return DELIV_PERIOD;
    }

    public void setDELIV_PERIOD(String DELIV_PERIOD) {
        this.DELIV_PERIOD = DELIV_PERIOD;
    }
}
