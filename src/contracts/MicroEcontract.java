package contracts;

public class MicroEcontract {

    private long fraction;

    private Econtract econtract;

    public MicroEcontract(long fraction) {
        this.fraction = fraction;
    }

    public long getFraction() {
        return fraction;
    }

    public void setFraction(long fraction) {
        this.fraction = fraction;
    }

}
