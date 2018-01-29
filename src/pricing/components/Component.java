package pricing.components;

import pricing.categories.UsageDependent;

public abstract class Component extends UsageDependent {
    private String label;

    
    /**
     * @see UsageDependent
     * @param reference
     * @param price
     * @param label String
     */
    public Component(Object reference, double price, String label) {
        super(reference, price);
        this.label = label;
    }

    /**
     * 
     * @return label String
     */
    public String getLabel() {
        return label;
    }

    /**
     * 
     * @param label label String
     */
    public void setLabel(String label) {
        this.label = label;
    }
    
}
