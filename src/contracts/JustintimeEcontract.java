package contracts;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JustintimeEcontract {

    private Date timeToStart;
    private SimpleDateFormat timeToStartSdf;
    private long timeToStartLong;

    protected JustintimeEcontract() {
        this.timeToStart = new Date();
    }

    public Date getTimeToStart() {
        return timeToStart;
    }

    protected void setTimeToStart(Date timeToStart) {
        this.timeToStart = timeToStart;
    }

    public SimpleDateFormat getTimeToStartSdf() {
        return timeToStartSdf;
    }

    protected void setTimeToStartSdf(SimpleDateFormat timeToStartSdf) {
        this.timeToStartSdf = timeToStartSdf;
    }

    public long getTimeToStartLong() {
        return timeToStartLong;
    }

    protected void setTimeToStartLong(long timeToStartLong) {
        this.timeToStartLong = timeToStartLong;
    }

}
