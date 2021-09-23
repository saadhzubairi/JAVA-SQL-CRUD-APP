package sample;

public class SUPPLIER_O {
    private int SUPNR;
    private String SUPNAME;
    private String SUPADDRESS;
    private String SUPCITY;
    private String SUPSTATUS;

    public SUPPLIER_O() { }

    public SUPPLIER_O(int SUPNR, String SUPNAME, String SUPADDRESS, String SUPCITY, String SUPSTATUS) {
        this.SUPNR = SUPNR;
        this.SUPNAME = SUPNAME;
        this.SUPADDRESS = SUPADDRESS;
        this.SUPCITY = SUPCITY;
        this.SUPSTATUS = SUPSTATUS;
    }

    @Override
    public String toString() {
        return "SUPPLIER{" +
                "SUPNR=" + SUPNR +
                ", SUPNAME='" + SUPNAME + '\'' +
                ", SUPADDRESS='" + SUPADDRESS + '\'' +
                ", SUPCITY='" + SUPCITY + '\'' +
                ", SUPSTATUS='" + SUPSTATUS + '\'' +
                '}';
    }

    public int getSUPNR() {
        return SUPNR;
    }

    public void setSUPNR(int SUPNR) {
        this.SUPNR = SUPNR;
    }

    public String getSUPNAME() {
        return SUPNAME;
    }

    public void setSUPNAME(String SUPNAME) {
        this.SUPNAME = SUPNAME;
    }

    public String getSUPADDRESS() {
        return SUPADDRESS;
    }

    public void setSUPADDRESS(String SUPADDRESS) {
        this.SUPADDRESS = SUPADDRESS;
    }

    public String getSUPCITY() {
        return SUPCITY;
    }

    public void setSUPCITY(String SUPCITY) {
        this.SUPCITY = SUPCITY;
    }

    public String getSUPSTATUS() {
        return SUPSTATUS;
    }

    public void setSUPSTATUS(String SUPSTATUS) {
        this.SUPSTATUS = SUPSTATUS;
    }
}
