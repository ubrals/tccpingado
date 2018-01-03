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

    public int isValidInt() {
        if(valid == true)
            return 1;
        return 0;
    }

    public void setValidFalse() {
        this.valid = false;
    }

    public void setValidTrue() {
        this.valid = true;
    }

}
