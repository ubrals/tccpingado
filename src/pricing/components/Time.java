package pricing.components;

import pricing.categories.UsageDependent;

public class Time extends UsageDependent {

	private TimeShares duration;

	public Time(TimeShares duration) {
	    this.duration = duration;
	}

    public TimeShares getDuration() {
        return duration;
    }

    public String getDurationLabel() {
        return duration.toString();
    }

	public void setDuration(TimeShares duration) {
	    this.duration = duration;
	}

}
