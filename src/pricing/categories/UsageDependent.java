package pricing.categories;

import pricing.orientations.Value;

public abstract class UsageDependent extends Value {

    public UsageDependent(Object reference, double price) {
        super(reference, price);
    }

}
