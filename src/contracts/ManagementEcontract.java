package contracts;

enum Status {
    INITIATED,
    STARTED,
    PROVISIONING,
    CONCLUDED,
    ABORTED
}

class ManagementEcontract {
    private Status status;

    public ManagementEcontract() {
        this.status = Status.INITIATED;
    }
    
}
