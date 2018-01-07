package contracts;

public class MicroEcontract {

    private int fraction;

    protected MicroEcontract(int fraction) {
        this.fraction = fraction;
    }

    protected long getFraction() {
        return fraction;
    }

    protected void setFraction(int fraction) {
        this.fraction = fraction;
    }

}
