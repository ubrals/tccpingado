package contracts;

import java.util.Collection;
import entities.Transaction;
import entities.Party;
import entities.values.ExchangedValue;
import pricing.Framework;

public class Econtract {

    private long id;

    private ExchangedValue exchangedValue;

    private Collection<Party> parties;

    private Collection<Transaction> transactions;

    private MicroEcontract microEcontract;

    private JustintimeEcontract justintimeEcontract;

    private EnactmentEcontract enactmentEcontract;

    private ManagementEcontract managementEcontract;

    private Framework framework;

    protected Econtract() {
        setId();
    }

    /**
     * 
     * @return long as Econtract id
     */
    public long getId() {
        return this.id;
    }

    /**
     * Automatic setup of Econtract id
     */
    private void setId() {
		// TODO: aqui vai pegar do banco de dados, tipo next sequence
        double rnd = Math.random();
        long id = (long) (rnd * 1000000000000l);
        this.id = id;
    }

    /**
     * 
     * @param id long as Econtract id
     */
    protected void setId(long id) {
        this.id = id;
    }

    /**
     * 
     * @return {@link Collection} of {@link Party}
     */
    public Collection<Party> getParty() {
        return parties;
    }

    /**
     * 
     * @param party {@link Collection} of {@link Party} related to the {@link Econtract}
     */
    protected void setParty(Collection<Party> party) {
        this.parties = party;
    }

    /**
     * 
     * @return {@link ExchangedValue} of the {@link Econtract}
     */
    public ExchangedValue getExchangedValue() {
        return exchangedValue;
    }

    /**
     * 
     * @param exchangedValue {@link ExchangedValue}
     */
    protected void setExchangedValue(ExchangedValue exchangedValue) {
        this.exchangedValue = exchangedValue;
    }

    /**
     * 
     * @return {@link Collection} of {@link Transaction}
     */
    public Collection<Transaction> getTransactions() {
        return transactions;
    }

    /**
     * 
     * @param transaction {@link Transaction}
     */
    protected void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    /**
     * 
     * @return {@link MicroEcontract} of the {@link Econtract}
     */
    public MicroEcontract getMicroEcontract() {
        return microEcontract;
    }

    /**
     * 
     * @param microEcontract {@link MicroEcontract}
     */
    protected void setMicroEcontract(MicroEcontract microEcontract) {
        this.microEcontract = microEcontract;
    }

    /**
     * 
     * @return {@link JustintimeEcontract} of the {@link Econtract}
     */
    public JustintimeEcontract getJustintimeEcontract() {
        return justintimeEcontract;
    }

    /**
     * 
     * @param justintimeEcontract {@link JustintimeEcontract}
     */
    protected void setJustintimeEcontract(JustintimeEcontract justintimeEcontract) {
        this.justintimeEcontract = justintimeEcontract;
    }

    /**
     * 
     * @return {@link EnactmentEcontract} of the {@link Econtract}
     */
    public EnactmentEcontract getEnactmentEcontract() {
        return enactmentEcontract;
    }

    /**
     * 
     * @param enactmentEcontract {@link EnactmentEcontract}
     */
    protected void setEnactmentEcontract(EnactmentEcontract enactmentEcontract) {
        this.enactmentEcontract = enactmentEcontract;
    }

    /**
     * 
     * @return {@link ManagementEcontract} of the {@link Econtract}
     */
    public ManagementEcontract getManagementEcontract() {
        return managementEcontract;
    }

    /**
     * 
     * @param managementEcontract {@link ManagementEcontract}
     */
    protected void setManagementEcontract(ManagementEcontract managementEcontract) {
        this.managementEcontract = managementEcontract;
    }

    /**
     * 
     * @return {@link Framework} of the {@link Econtract}
     */
    public Framework getFramework() {
        return framework;
    }

    /**
     * 
     * @param framework {@link Framework}
     */
    protected void setFramework(Framework framework) {
        this.framework = framework;
    }

}
