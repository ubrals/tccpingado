package contracts;

public class EnactmentEcontract {

    private boolean valid;

    protected EnactmentEcontract() {
        this.valid = false;
    }

    public boolean isValid() {
        return valid;
    }

    public int isValidInt() {
        if(this.valid == true)
            return 1;
        return 0;
    }

    protected void setValidFalse() {
        this.valid = false;
    }

    protected void setValidTrue() {
        this.valid = true;
    }
    
    protected void setValidInt(int valid){
        if(valid == 1) this.valid = true;
        else this.valid = false;
    }

}
