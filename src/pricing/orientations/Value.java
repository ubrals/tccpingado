package pricing.orientations;

import pricing.Framework;

public abstract class Value extends Framework {

    /**
     * @see Framework
     * @param reference
     * @param price
     */
    public Value(Object reference, double price) {
        super(reference, price);
    }

}
