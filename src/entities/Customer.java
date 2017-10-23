package entities;

import java.util.Collection;
import entities.values.Content;

public class Customer extends CryptoPerson {

	public Customer(long id, String name) {
        super(id, name);
        // TODO Auto-generated constructor stub
    }

    private Collection<Content> content;

}
