package pricing.components;

import pricing.categories.UsageDependent;

public class Time extends UsageDependent {

	private TimeShares share;

	public Time(TimeShares share) {
	    this.share = share;
	}

    public TimeShares getShare() {
        return share;
    }

    public String getShareLabel() {
        return share.toString();
    }

	public void setShare(TimeShares share) {
	    this.share = share;
	}
	
	public void setShare(String share) throws Exception{
	    switch (share.toUpperCase()) {
        case "DAY": this.share = TimeShares.DAY; break;
        case "HOUR": this.share = TimeShares.HOUR; break;
        case "MINUTE": this.share = TimeShares.MINUTE; break;
        case "SECOND": this.share = TimeShares.SECOND; break;
        case "MILLISECOND": this.share = TimeShares.MILLISECOND; break;
        default: throw new Exception("..:ERR:Time share is not allowed");
        }
	}

}
