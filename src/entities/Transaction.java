package entities;

public class Transaction {

    private long id;
    private long timestamp;
    private double price;

    public Transaction(long id, long trxtimestamp, double price) {
        this.id = id;
        this.setTrxtimestamp(trxtimestamp);
        this.setPrice(price);
    }

    public long getId() {
        return id;
    }

    private void setId() {
        double rnd = Math.random();
        long id = (long) (rnd * 1000000000l);
        this.id = (long) Math.random();
    }

    public long getTrxtimestamp() {
        return timestamp;
    }

    public void setTrxtimestamp(long trxtimestamp) {
        this.timestamp = trxtimestamp;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
