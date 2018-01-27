package contracts;

public class MicroEcontract {

    private int fraction;

    /**
     * Create new Micro Econtract
     * @param fraction int as time frame
     */
    protected MicroEcontract(int fraction) {
        this.fraction = fraction;
    }

    /**
     * 
     * @return fraction int as time frame
     */
    public int getFraction() {
        return fraction;
    }

    /**
     * 
     * @param fraction int as time frame
     */
    protected void setFraction(int fraction) {
        this.fraction = fraction;
    }

}
