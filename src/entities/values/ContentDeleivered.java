package entities.values;

public class ContentDeleivered {
    private String url;
    private long contentId;
    private long econtractId;
    private long customerId;
    private int  debitAmount;
    
    public ContentDeleivered(String url, long contentId, long econtractId, long customerId, int debitAmount) {
        this.url = url;
        this.contentId = contentId;
        this.econtractId = econtractId;
        this.customerId = customerId;
        this.debitAmount = debitAmount;
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

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }
    
    
}
