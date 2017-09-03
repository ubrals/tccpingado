package contracts;

import java.util.ArrayList;
import java.util.List;

import entities.Party;
import entities.Transaction;
import pricing.Framework;

public class Econtract {
    private Framework framework;
    private Party consumer;
    private Party provider;
    private List transactions = new ArrayList<Transaction>();
    
    private MicroEcontract microecontract;
    private JustintimeEcontract justintimeecontract;
    private ManagementEcontract managementecontract;
    private EnacmentEcontract enactmentecontract;
    
    public Econtract() {
        
    }

}
