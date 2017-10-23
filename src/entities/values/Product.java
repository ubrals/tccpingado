package entities.values;

import contracts.Econtract;

public abstract class Product extends ExchangedValue {

	public Product(String type, String value, Econtract econtract) {
        super(type, value, econtract);
        // TODO Auto-generated constructor stub
    }

    private String subType;

}
