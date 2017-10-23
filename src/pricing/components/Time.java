package pricing.components;

import pricing.categories.UsageDependent;

public class Time extends UsageDependent {

	private long duration;

	public Time(long duration) {

	}

	public long getDuration() {
	    return duration;
	}

	public void setDuration(long duration) {
	    this.duration=duration;
	}

}
