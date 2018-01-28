package contracts;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class JustintimeEcontract {

    private Date timeToStart;
    private SimpleDateFormat timeToStartSdf;
    private long timeToStartLong;

    /*
     * Create Justintime Econtract to start immediately
     */
    protected JustintimeEcontract() {
        this.timeToStart = new Date();
        this.timeToStartLong = this.timeToStart.getTime();
    }

    /**
     * 
     * @return timeToStart Date
     */
    public Date getTimeToStart() {
        return timeToStart;
    }

    /**
     * 
     * @param timeToStart Date
     */
    protected void setTimeToStart(Date timeToStart) {
        this.timeToStart = timeToStart;
        this.timeToStartLong = this.timeToStart.getTime();
    }

    /**
     * 
     * @return timeToStartSdf timeToStartSdf
     * @deprecated
     */
    public SimpleDateFormat getTimeToStartSdf() {
        return timeToStartSdf;
    }

    /**
     * 
     * @param timeToStartSdf SimpleDateFormat
     */
    protected void setTimeToStartSdf(SimpleDateFormat timeToStartSdf) {
        this.timeToStartSdf = timeToStartSdf;
    }

    /**
     * 
     * @return timeToStartLong long as timestamp setup by System.currentTimeMillis()
     */
    public long getTimeToStartLong() {
        return timeToStartLong;
    }

    /**
     * 
     * @param timeToStartLong long as timestamp setup by System.currentTimeMillis() 
     */
    protected void setTimeToStartLong(long timeToStartLong) {
        this.timeToStartLong = timeToStartLong;
        this.timeToStart = new Date(this.timeToStartLong);
    }

}
