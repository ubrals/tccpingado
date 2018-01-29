package pricing;

public abstract class Framework {
    private Object reference;
    private double price;
    
    /**
     * 
     * @param reference Object
     * @param price double
     */
    public Framework(Object reference, double price) {
        this.setReference(reference);
        this.setPrice(price);
    }

    /**
     * 
     * @return reference Object
     */
    public Object getReference() {
        return reference;
    }

    /**
     * 
     * @param reference Object
     */
    public void setReference(Object reference) {
        this.reference = reference;
    }

    /**
     * 
     * @return price double
     */
    public double getPrice() {
        return price;
    }

    /**
     * 
     * @param price double
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
}
