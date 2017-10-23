package entities.values;

import contracts.Econtract;

public abstract class ExchangedValue {

	private String type;

	private String value;

	private Econtract econtract;

    public ExchangedValue(String type, String value, Econtract econtract) {
        this.type = type;
        this.value = value;
        this.econtract = econtract;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Econtract getEcontract() {
        return econtract;
    }

    public void setEcontract(Econtract econtract) {
        this.econtract = econtract;
    }

}
