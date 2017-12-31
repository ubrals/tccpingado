package entities.values;

import contracts.Econtract;

public abstract class Product extends ExchangedValue {
	private String subType;

	public Product(String type, String subType, String value, Econtract econtract) {
        super(type, value, econtract);
        this.setSubType(subType);
    }

    public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

}
