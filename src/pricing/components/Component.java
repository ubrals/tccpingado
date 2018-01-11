package pricing.components;

import pricing.categories.UsageDependent;

public abstract class Component extends UsageDependent {
    private String label;

    
    /**
     * @param reference
     * @param price
     * @param label
     */
    public Component(Object reference, double price, String label) {
        super(reference, price);
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
}
