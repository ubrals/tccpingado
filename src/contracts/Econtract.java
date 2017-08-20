package contracts;

import java.util.ArrayList;
import java.util.List;

import entities.CSP;
import entities.CriptoPerson;
import entities.Transaction;
import pricing.Framework;

public class Econtract {
    Framework framework;
    private CSP csp;
    private CriptoPerson criptoperson;
    private List transactions = new ArrayList<Transaction>();
    
    public Econtract() {
        // TODO Auto-generated constructor stub
    }

}
