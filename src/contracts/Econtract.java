package contracts;

import java.util.Collection;
import entities.Transaction;
import entities.Party;
import entities.values.ExchangedValue;
import pricing.Framework;

public class Econtract {

    private long id;

    private Collection<Transaction> transactions;

    private MicroEcontract microEcontract;

    private JustintimeEcontract justintimeEcontract;

    private ManagementEcontract managementEcontract;

    private EnactmentEcontract enactmentEcontract;

    private Collection<Party> party;

    private ExchangedValue exchangedValue;

    private Framework framework;

    private Transaction transaction;

    public Econtract() {
        setId();
    }

    public long getId() {
        return this.id;
    }

    private void setId() {
		// TODO: aqui vai pegar do banco de dados, tipo next sequence
        double rnd = Math.random();
        long id = (long) (rnd * 1000000000000l);
        this.id = id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Collection<Party> getParty() {
        return party;
    }

    public void setParty(Collection<Party> party) {
        this.party = party;
    }

    public ExchangedValue getExchangedValue() {
        return exchangedValue;
    }

    public void setExchangedValue(ExchangedValue exchangedValue) {
        this.exchangedValue = exchangedValue;
    }

    public Collection getTransactions() {
        return null;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public MicroEcontract getMicroEcontract() {
        return microEcontract;
    }

    public void setMicroEcontract(MicroEcontract microEcontract) {
        this.microEcontract = microEcontract;
    }

    public JustintimeEcontract getJustintimeEcontract() {
        return justintimeEcontract;
    }

    public void setJustintimeEcontract(JustintimeEcontract justintimeEcontract) {
        this.justintimeEcontract = justintimeEcontract;
    }

    public EnactmentEcontract getEnactmentEcontract() {
        return enactmentEcontract;
    }

    public void setEnactmentEcontract(EnactmentEcontract enactmentEcontract) {
        this.enactmentEcontract = enactmentEcontract;
    }

    public ManagementEcontract getManagementEcontract() {
        return managementEcontract;
    }

    public void setManagementEcontract(ManagementEcontract managementEcontract) {
        this.managementEcontract = managementEcontract;
    }

}
