package pricing.components;

public class Time extends Component {

    /**
     * @param reference
     * @param price
     * @param label
     */
    public Time(TimeShares reference, double price, String label) {
        super(reference, price, label);
    }
    public Time(String reference, double price, String label) {
        super(reference, price, label);
        try {
            setReference(reference);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
	@Override
    public String getLabel() {
        return super.getLabel();
    }
    
    @Override
    public void setLabel(String label) {
        super.setLabel(label);
    }
    
    @Override
    public Object getReference() {
        return super.getReference();
    }
    public String getReferenceString() {
        return super.getReference().toString();
    }
    
    public void setReference(TimeShares reference) {
        super.setReference(reference);
    }
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
    
    @Override
    public double getPrice() {
        return super.getPrice();
    }
    
    @Override
    public void setPrice(double price) {
        super.setPrice(price);
    }
}
