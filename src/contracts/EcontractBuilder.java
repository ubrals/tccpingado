package contracts;

import java.util.ArrayList;
import java.util.Collection;

import entities.Party;
import entities.values.Content;
import pricing.Framework;
import pricing.components.Time;

public class EcontractBuilder {
    
    private Econtract econtract;
    
    protected EcontractBuilder(){
    }
    
    /**
     * Start to build the Econtract
     */
    protected void buildEcontract(){
        econtract = new Econtract();
    }

    /**
     * 
     * @param id long as Econtract id
     */
    protected void setEcontractId(long id){
        econtract.setId(id);
    }

    /**
     * 
     * @param provider {@link Party} providing the ExchangedValue
     * @param consumer {@link Party} consuming the ExchangedValue
     * @throws Exception
     */
    protected void setParties(Party provider, Party consumer) throws Exception {
        Collection<Party> parties = new ArrayList<>();
        parties.add(provider);
        parties.add(consumer);
        econtract.setParty(parties);
    }

    /**
     * 
     * @param content {@link Content}
     * @throws Exception
     */
    protected void setExchangedValue(Content content) throws Exception {
        econtract.setExchangedValue(content);
    }

    /**
     * 
     * @param framework {@link Framework}
     * @param frameworkReference String as reference to calculate price 
     * @param frameworkPrice double as price
     * @throws Exception
     */
    protected void setFramework(Framework framework, String frameworkReference, double frameworkPrice) throws Exception {
        if(framework instanceof Time){
            framework.setReference(frameworkReference);
            framework.setPrice(frameworkPrice);
            econtract.setFramework(framework);
        }
        else
            throw new Exception("..:ERR:" + this.getClass().getSimpleName() + ":Component of the Framework is not defined");
    }

    /**
     * 
     * @param fractionMicro int as time frame
     * @throws Exception
     */
    protected void setMicroEcontract(int fractionMicro) throws Exception {
        MicroEcontract microEcontract = new MicroEcontract(fractionMicro);
        econtract.setMicroEcontract(microEcontract);
    }
    
    /**
     * Sets timestamp according to the by System.currentTimeMillis()
     * @throws Exception
     */
    protected void setJustintimeEcontract() throws Exception {
        JustintimeEcontract justintimeEcontract = new JustintimeEcontract();
        long timeToStart = System.currentTimeMillis();
        justintimeEcontract.setTimeToStartLong(timeToStart);
        econtract.setJustintimeEcontract(justintimeEcontract);
    }
    
    /**
     * 
     * @param timeToStart long as timestamp setup by System.currentTimeMillis()
     * @throws Exception
     */
    protected void setJustintimeEcontract(long timeToStart) throws Exception {
        JustintimeEcontract justintimeEcontract = new JustintimeEcontract();
        justintimeEcontract.setTimeToStartLong(timeToStart);
        econtract.setJustintimeEcontract(justintimeEcontract);
    }
    
    /**
     * Sets Econtract as valid, as true
     * @throws Exception
     */
    protected void setEnactmentEcontract() throws Exception {
        EnactmentEcontract enactmentEcontract = new EnactmentEcontract();
        enactmentEcontract.setValidTrue();
        econtract.setEnactmentEcontract(enactmentEcontract);
    }
    
    /**
     * 
     * @param valid int as validity of Econtract
     * @throws Exception
     */
    protected void setEnactmentEcontract(int valid) throws Exception {
        EnactmentEcontract enactmentEcontract = new EnactmentEcontract();
        enactmentEcontract.setValidInt(valid);
        econtract.setEnactmentEcontract(enactmentEcontract);
    }
    
    /**
     * Sets Econtract to {@link Status}.INITIATED
     * @throws Exception
     */
    protected void setManagementEcontract() throws Exception {
        ManagementEcontract managementEcontract = new ManagementEcontract();
        managementEcontract.setStatus(1);
        econtract.setManagementEcontract(managementEcontract);
    }
    
    /**
     * 
     * @param status int as status of Econtract
     * @throws Exception
     */
    protected void setManagementEcontract(int status) throws Exception {
        ManagementEcontract managementEcontract = new ManagementEcontract();
        managementEcontract.setStatus(status);
        econtract.setManagementEcontract(managementEcontract);
    }
    
    /**
     * 
     * @return {@link Econtract} built
     */
    protected Econtract getObject(){
        return this.econtract;
    }

}
