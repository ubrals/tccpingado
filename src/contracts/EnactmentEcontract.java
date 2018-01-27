package contracts;

public class EnactmentEcontract {

    private boolean valid;

    /**
     * Creates Enactment Econtract initially as false
     */
    protected EnactmentEcontract() {
        this.valid = false;
    }

    /**
     * 
     * @return boolean as validity
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * 
     * @return int as validity: 1 is true; 0 is false
     */
    public int isValidInt() {
        if(this.valid == true)
            return 1;
        return 0;
    }

    /**
     * Set false
     */
    protected void setValidFalse() {
        this.valid = false;
    }

    /**
     * Set true
     */
    protected void setValidTrue() {
        this.valid = true;
    }
    
    /**
     * 
     * @param valid int as validity: 1 is true; 0 is false
     */
    protected void setValidInt(int valid){
        if(valid == 1) this.valid = true;
        else this.valid = false;
    }

}
