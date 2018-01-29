package entities;

public class Transaction {

    private long id;
    private long timestamp;
    private double price;

    /**
     * 
     * @param id long as Transaction id
     * @param trxtimestamp long as timestamp setup by System.currentTimeMillis()
     * @param price double
     */
    public Transaction(long id, long trxtimestamp, double price) {
        this.id = id;
        this.setTrxtimestamp(trxtimestamp);
        this.setPrice(price);
    }

    /**
     * 
     * @return id long
     */
    public long getId() {
        return id;
    }

    /**
     * Automatically generates id
     * @deprecated
     */
    private void setId() {
        double rnd = Math.random();
        long id = (long) (rnd * 1000000000l);
        this.id = (long) Math.random();
    }

    /**
     * 
     * @param id long
     */
    public void setId(long id){
        this.id = id;
    }
    
    /**
     * 
     * @return long as timestamp setup by System.currentTimeMillis()
     */
    public long getTrxtimestamp() {
        return timestamp;
    }

    /**
     * 
     * @param trxtimestamp long as timestamp setup by System.currentTimeMillis()
     */
    public void setTrxtimestamp(long trxtimestamp) {
        this.timestamp = trxtimestamp;
    }

    /**
     * 
     * @return price double
     */
    public double getPrice() {
        return price;
    }

    /**
     * 
     * @param price double
     */
    public void setPrice(double price) {
        this.price = price;
    }

}
