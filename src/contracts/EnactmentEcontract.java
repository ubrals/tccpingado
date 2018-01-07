package contracts;

public class EnactmentEcontract {

    private boolean valid;

    protected EnactmentEcontract() {
        this.valid = false;
    }

    protected boolean isValid() {
        return valid;
    }

    protected int isValidInt() {
        if(valid == true)
            return 1;
        return 0;
    }

    protected void setValidFalse() {
        this.valid = false;
    }

    protected void setValidTrue() {
        this.valid = true;
    }

}
