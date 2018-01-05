package entities;

public class Transaction {

    private long id;

    public Transaction(long id) {
        setId();
    }

    public long getId() {
        return id;
    }

    private void setId() {
        double rnd = Math.random();
        long id = (long) (rnd * 1000000000l);
        this.id = (long) Math.random();
    }

}
