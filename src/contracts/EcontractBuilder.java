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
    
    protected void buildEcontract(){
        econtract = new Econtract();
    }
    
    protected void setEcontractId(long id){
        econtract.setId(id);
    }
    
    protected void setParties(Party provider, Party consumer) throws Exception {
        Collection<Party> parties = new ArrayList<>();
        parties.add(provider);
        parties.add(consumer);
        econtract.setParty(parties);
    }
    
    protected void setExchangedValue(Content content) throws Exception {
        econtract.setExchangedValue(content);
    }
    
    protected void setFramework(Framework framework, String reference, double price) throws Exception {
        if(framework instanceof Time){
            framework.setReference(reference);
            framework.setPrice(price);
            econtract.setFramework(framework);
        }
    }
    
    protected void setMicroEcontract(int fractionMicro) throws Exception {
        MicroEcontract microEcontract = new MicroEcontract(fractionMicro);
        econtract.setMicroEcontract(microEcontract);
    }
    
    protected void setJustintimeEcontract() throws Exception {
        JustintimeEcontract justintimeEcontract = new JustintimeEcontract();
        long timeToStart = System.currentTimeMillis();
        justintimeEcontract.setTimeToStartLong(timeToStart);
        econtract.setJustintimeEcontract(justintimeEcontract);
    }
    
    protected void setJustintimeEcontract(long timeToStart) throws Exception {
        JustintimeEcontract justintimeEcontract = new JustintimeEcontract();
        justintimeEcontract.setTimeToStartLong(timeToStart);
        econtract.setJustintimeEcontract(justintimeEcontract);
    }
    
    protected void setEnactmentEcontract() throws Exception {
        EnactmentEcontract enactmentEcontract = new EnactmentEcontract();
        enactmentEcontract.setValidTrue();
        econtract.setEnactmentEcontract(enactmentEcontract);
    }
    
    protected void setEnactmentEcontract(int valid) throws Exception {
        EnactmentEcontract enactmentEcontract = new EnactmentEcontract();
        enactmentEcontract.setValidInt(valid);
        econtract.setEnactmentEcontract(enactmentEcontract);
    }
    
    protected void setManagementEcontract() throws Exception {
        ManagementEcontract managementEcontract = new ManagementEcontract();
        managementEcontract.setStatus(1);
        econtract.setManagementEcontract(managementEcontract);
    }
    
    protected void setManagementEcontract(int status) throws Exception {
        ManagementEcontract managementEcontract = new ManagementEcontract();
        managementEcontract.setStatus(status);
        econtract.setManagementEcontract(managementEcontract);
    }
    
    protected Econtract getObject(){
        return this.econtract;
    }

}
