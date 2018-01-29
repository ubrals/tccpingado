package entities.values;

import contracts.Econtract;

public abstract class ExchangedValue {

	private String type;

	private String value;

	private Econtract econtract;

	/**
	 * 
	 * @param type String as type of media
	 * @param value String nevermind
	 * @param econtract {@link Econtract}
	 */
    public ExchangedValue(String type, String value, Econtract econtract) {
        this.type = type;
        this.value = value;
        this.econtract = econtract;
    }

    /**
     * 
     * @return type String as type of media
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type String as type of media
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return value String nevermind
     */
    public String getValue() {
        return value;
    }

    /**
     * 
     * @param value String nevermind
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 
     * @return econtract {@link Econtract}
     */
    public Econtract getEcontract() {
        return econtract;
    }

    /**
     * 
     * @param econtract {@link Econtract}
     */
    public void setEcontract(Econtract econtract) {
        this.econtract = econtract;
    }

}
