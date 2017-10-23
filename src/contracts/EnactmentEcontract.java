package contracts;

public class EnactmentEcontract {

    private boolean valid;

    private Econtract econtract;

    public EnactmentEcontract() {
        this.valid = false;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValidFalse() {
        this.valid = false;
    }

    public void setValidTrue() {
        this.valid = true;
    }

}
