package pricing;

public abstract class Framework {
    private Object reference;
    private double price;
    
    public Framework(Object reference, double price) {
        this.setReference(reference);
        this.setPrice(price);
    }

    public Object getReference() {
        return reference;
    }

    public void setReference(Object reference) {
        this.reference = reference;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
}
