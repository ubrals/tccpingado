package entities.values;

import contracts.Econtract;

public abstract class Product extends ExchangedValue {
	private String subType;

	/**
	 * 
	 * @param type String as type of media
	 * @param subType String as classification
	 * @param value String nevermind
	 * @param econtract {@link Econtract}
	 */
	public Product(String type, String subType, String value, Econtract econtract) {
        super(type, value, econtract);
        this.setSubType(subType);
    }

	/**
	 * 
	 * @return subType String as classification
	 */
    public String getSubType() {
		return subType;
	}

    /**
     * 
     * @param subType String as classification
     */
	public void setSubType(String subType) {
		this.subType = subType;
	}

}
