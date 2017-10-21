package contracts;

import java.util.Date;

class JustintimeEcontract {
    private Date timeToStart;

    public JustintimeEcontract(Date timeToStart) {
        super();
        this.timeToStart = timeToStart;
    }

    public Date getTimeToStart() {
        return timeToStart;
    }

    public void setTimeToStart(Date timeToStart) {
        this.timeToStart = timeToStart;
    }
    
}
