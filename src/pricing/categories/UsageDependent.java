package pricing.categories;

import pricing.orientations.Value;

public abstract class UsageDependent extends Value {

    /**
     * @see Value
     * @param reference
     * @param price
     */
    public UsageDependent(Object reference, double price) {
        super(reference, price);
    }

}
