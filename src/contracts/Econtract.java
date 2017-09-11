package contracts;

import java.util.ArrayList;
import java.util.List;

import entities.Party;
import entities.Transaction;
import entities.values.ExchangedValue;
import entities.values.ExchangedValueProvision;
import pricing.Framework;

public class Econtract {
    private Framework framework;
    private Party consumer;
    private Party provider;
    private ExchangedValue content;
    private List provisions = new ArrayList<ExchangedValueProvision>();
    private List transactions = new ArrayList<Transaction>();
    
    private MicroEcontract microecontract;
    private JustintimeEcontract justintimeecontract;
    private ManagementEcontract managementecontract;
    private EnacmentEcontract enactmentecontract;
    
    public Econtract() {
        
    }

}
