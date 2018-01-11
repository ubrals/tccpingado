package entities.values;

public class ContentDelivered {
    private String url;
    private long   contentId;
    private long   econtractId;
    private long   consumerId;
    private String timeReference;
    private double debitAmount;
    
    public ContentDelivered(String url, long contentId, long econtractId, long consumerId, String timeReference, double debitAmount) {
        this.url = url;
        this.contentId = contentId;
        this.econtractId = econtractId;
        this.consumerId = consumerId;
        this.setTimeReference(timeReference);
        this.setDebitAmount(debitAmount);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getContentId() {
        return contentId;
    }

    public void setContentId(long contentId) {
        this.contentId = contentId;
    }

    public long getEcontractId() {
        return econtractId;
    }

    public void setEcontractId(long econtractId) {
        this.econtractId = econtractId;
    }

    public long getConsumerId() {
        return consumerId;
    }

    public void setCustomerId(long customerId) {
        this.consumerId = customerId;
    }

    public String getTimeReference() {
        return timeReference;
    }

    public void setTimeReference(String timeReference) {
        this.timeReference = timeReference;
    }

    public double getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(double debitAmount) {
        this.debitAmount = debitAmount;
    }
    
    
}
