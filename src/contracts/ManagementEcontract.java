package contracts;

public class ManagementEcontract {

    private Status status;

    /**
     * Create Management Econtract initiallly as Status.UNKNOWN
     */
    protected ManagementEcontract() {
        this.status = Status.UNKNOWN;
    }

    /**
     * 
     * @return status.toString() String UNKNOWN; INITIATED; STARTED; PROVISIONING; CONCLUDED; ABORTED
     */
    public String getStatusLabel(){
        return this.status.toString();
    }
    
    /**
     * 
     * @return status int 0:UNKNOWN; 1:INITIATED; 2:STARTED; 3:PROVISIONING; 4:CONCLUDED; 5:ABORTED
     */
    public int getStatus() {
        int status;
        switch (this.status) {
        case UNKNOWN:
            status = 0;
            break;
        case INITIATED:
            status = 1;
            break;
        case STARTED:
            status = 2;
            break;
        case PROVISIONING:
            status = 3;
            break;
        case CONCLUDED:
            status = 4;
        case ABORTED:
            status = 5;
            break;
        default:
            status = 0;
            break;
        }
        return status;
    }

    /**
     * 
     * @param status {@link Status}
     */
    protected void setStatus(Status status) {
        this.status = status;
    }

    /**
     * 
     * @param status int 0:UNKNOWN; 1:INITIATED; 2:STARTED; 3:PROVISIONING; 4:CONCLUDED; 5:ABORTED
     */
    protected void setStatus(int status) {
        switch (status) {
        case 0:
            this.status = Status.UNKNOWN;
            break;
        case 1:
            this.status = Status.INITIATED;
            break;
        case 2:
            this.status = Status.STARTED;
            break;
        case 3:
            this.status = Status.PROVISIONING;
            break;
        case 4:
            this.status = Status.CONCLUDED;
        case 5:
            this.status = Status.ABORTED;
            break;
        default:
            this.status = Status.UNKNOWN;
            break;
        }
    }

}
