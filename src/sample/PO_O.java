package sample;

public class PO_O {
    public int PONR;
    public String PODATE;
    public int SUPNR;

    public PO_O() { }

    public PO_O(int PONR, String PODATE, int SUPNR) {
        this.PONR = PONR;
        this.PODATE = PODATE;
        this.SUPNR = SUPNR;
    }

    public int getPONR() {
        return PONR;
    }

    public void setPONR(int PONR) {
        this.PONR = PONR;
    }

    public String getPODATE() {
        return PODATE;
    }

    public void setPODATE(String PODATE) {
        this.PODATE = PODATE;
    }

    public int getSUPNR() {
        return SUPNR;
    }

    public void setSUPNR(int SUPNR) {
        this.SUPNR = SUPNR;
    }
}
