package contracts;

public class ManagementEcontract {

    private Status status;

    private Econtract econtract;

    public ManagementEcontract() {
        this.status = Status.UNKNOWN;
    }

    public String getStatusLabel(){
        return this.status.toString();
    }
    
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

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setStatus(int status) {
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
