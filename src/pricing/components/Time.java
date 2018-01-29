package pricing.components;

public class Time extends Component {

    /**
     * @see Component
     * @param reference {@link TimeShares}
     * @param price
     * @param label
     */
    public Time(TimeShares reference, double price, String label) {
        super(reference, price, label);
    }
    /**
     * 
     * @param reference String
     * @param price
     * @param label
     */
    public Time(String reference, double price, String label) {
        super(reference, price, label);
        try {
            setReference(reference);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /*
     * (non-Javadoc)
     * @see pricing.components.Component#getLabel()
     */
	@Override
    public String getLabel() {
        return super.getLabel();
    }
    
	/*
	 * (non-Javadoc)
	 * @see pricing.components.Component#setLabel(java.lang.String)
	 */
    @Override
    public void setLabel(String label) {
        super.setLabel(label);
    }
    
    /*
     * (non-Javadoc)
     * @see pricing.Framework#getReference()
     */
    @Override
    public Object getReference() {
        return super.getReference();
    }
    /**
     * 
     * @return String
     */
    public String getReferenceString() {
        return super.getReference().toString();
    }
    
    /**
     * 
     * @param reference {@link TimeShares}
     */
    public void setReference(TimeShares reference) {
        super.setReference(reference);
    }
    /**
     * 
     * @param reference String as {@link TimeShares}
     * @throws Exception
     */
    public void setReference(String reference) throws Exception{
	    switch (reference.toUpperCase()) {
//      case "DAY": this.share = TimeShares.DAY; break;
//      case "HOUR": this.share = TimeShares.HOUR; break;
//      case "MINUTE": this.share = TimeShares.MINUTE; break;
//      case "SECOND": this.share = TimeShares.SECOND; break;
//      case "MILLISECOND": this.share = TimeShares.MILLISECOND; break;
        case "DAY":         super.setReference(TimeShares.DAY); break;
        case "HOUR":        super.setReference(TimeShares.HOUR); break;
        case "MINUTE":      super.setReference(TimeShares.MINUTE); break;        
        case "SECOND":      super.setReference(TimeShares.SECOND); break;        
        case "MILLISECOND": super.setReference(TimeShares.MILLISECOND); break;
        default: throw new Exception("..:ERR:Time share is not allowed");
        }
    }
    
    /*
     * (non-Javadoc)
     * @see pricing.Framework#getPrice()
     */
    @Override
    public double getPrice() {
        return super.getPrice();
    }
    
    /*
     * (non-Javadoc)
     * @see pricing.Framework#setPrice(double)
     */
    @Override
    public void setPrice(double price) {
        super.setPrice(price);
    }
}
