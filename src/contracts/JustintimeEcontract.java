package contracts;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JustintimeEcontract {

    private Date timeToStart;
    private SimpleDateFormat timeToStartSdf;
    private long timeToStartLong;

    private Econtract econtract;

    public JustintimeEcontract() {
        this.timeToStart = new Date();
    }

    public Date getTimeToStart() {
        return timeToStart;
    }

    public void setTimeToStart(Date timeToStart) {
        this.timeToStart = timeToStart;
    }

    public SimpleDateFormat getTimeToStartSdf() {
        return timeToStartSdf;
    }

    public void setTimeToStartSdf(SimpleDateFormat timeToStartSdf) {
        this.timeToStartSdf = timeToStartSdf;
    }

    public long getTimeToStartLong() {
        return timeToStartLong;
    }

    public void setTimeToStartLong(long timeToStartLong) {
        this.timeToStartLong = timeToStartLong;
    }

}
