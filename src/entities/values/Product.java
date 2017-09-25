package entities.values;

public abstract class Product extends ExchangedValue {
    
    public String subType;

    public Product(String type, String value, String subType) {
        super(type, value);
        this.subType = subType;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }
    
}
