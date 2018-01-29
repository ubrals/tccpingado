package software.provisioning._deprec_;

import entities.Party;
import entities.values.ContentDelivered;

public class deprec_PlayMedia {
    private Party isp;
    private ContentDelivered contentDelivered;
    private String url;
    private String time;
    private long sleep;

    public deprec_PlayMedia(Party isp, ContentDelivered contentDelivered){
        this.isp = isp;
        this.contentDelivered = contentDelivered;
        this.url = this.contentDelivered.getUrl();
        this.time = this.contentDelivered.getTimeReference();
        switch (time) {
        case "DAY":    this.sleep=1000l * 60l * 60l * 24l; break;
        case "HOUR":   this.sleep=1000l * 60l * 60l; break;
        case "MINUTE": this.sleep=1000l * 60l; break;
        case "SECOND": this.sleep=1000l; break;
        case "MILLISECOND": this.sleep=1l; break;
        default: this.sleep=1000l * 60l; break;
        }
    }
    
    
}
