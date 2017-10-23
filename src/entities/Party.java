package entities;

import contracts.Econtract;
import java.util.Collection;

public abstract class Party {

    private long id;

    private String name;

    private Econtract econtract;

    private Collection<Transaction> transaction;

    public Party(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
