package entities.values;

public class ContentDelivered {
    private String url;
    private long   contentId;
    private long   econtractId;
    private long   consumerId;
    private String password;
    private String account;
    private String frameworkReference;
    private double debitAmount;

    /**
     * 
     * @param url String as URL to access the content
     * @param contentId long as Content id
     * @param econtractId long as Econtract id
     * @param consumerId long as Party id of the consumer
     * @param password String as the password to unlock account/wallet
     * @param account String as account identification
     * @param frameworkReference String as reference to calculate price
     * @param debitAmount double as the price
     */
    public ContentDelivered(String url, long contentId, long econtractId, long consumerId, String password, String account, String frameworkReference, double debitAmount) {
        this.url = url;
        this.contentId = contentId;
        this.econtractId = econtractId;
        this.consumerId = consumerId;
        this.password = password;
        this.setAccount(account);
        this.frameworkReference = frameworkReference;
        this.debitAmount = debitAmount;
    }

    /**
     * 
     * @return url String as URL to access the content
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url String as URL to access the content
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 
     * @return contentId long as Content id
     */
    public long getContentId() {
        return contentId;
    }

    /**
     * 
     * @param contentId long as Content id
     */
    public void setContentId(long contentId) {
        this.contentId = contentId;
    }

    /**
     * 
     * @return econtractId long as Econtract id
     */
    public long getEcontractId() {
        return econtractId;
    }

    /**
     * 
     * @param econtractId long as Econtract id
     */
    public void setEcontractId(long econtractId) {
        this.econtractId = econtractId;
    }

    /**
     * 
     * @return consumerId long as Party id of the consumer
     */
    public long getConsumerId() {
        return consumerId;
    }

    /**
     * 
     * @param consumerId long as Party id of the consumer
     */
    public void setCustomerId(long customerId) {
        this.consumerId = customerId;
    }

    /**
     * 
     * @return password String as the password to unlock account/wallet
     */
    public String getPassword() {
        return password;
    }

    /**
     * 
     * @param password String as the password to unlock account/wallet
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 
     * @return account String as account identification
     */
    public String getAccount() {
        return account;
    }

    /**
     * 
     * @param account String as account identification
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 
     * @return frameworkReference String as reference to calculate price
     */
    public String getTimeReference() {
        return frameworkReference;
    }

    /**
     * 
     * @param frameworkReference String as reference to calculate price
     */
    public void setTimeReference(String frameworkReference) {
        this.frameworkReference = frameworkReference;
    }

    /**
     * 
     * @return debitAmount double as the price
     */
    public double getDebitAmount() {
        return debitAmount;
    }

    /**
     * 
     * @param debitAmount double as the price
     */
    public void setDebitAmount(double debitAmount) {
        this.debitAmount = debitAmount;
    }
        
}
