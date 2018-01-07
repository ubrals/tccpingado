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
    
    protected void setParties(Party provider, Party consumer) throws Exception {
        Collection<Party> parties = new ArrayList<>();
        parties.add(provider);
        parties.add(consumer);
        econtract.setParty(parties);
    }
    
    protected void setExchangedValue(Content content) throws Exception {
        econtract.setExchangedValue(content);
    }
    
    protected void setFramework(Framework framework, String value) throws Exception {
        if(framework instanceof Time){
            ((Time)framework).setShare(value);
            econtract.setFramework((Time)framework);
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
    
    protected void setEnactmentEcontract() throws Exception {
        EnactmentEcontract enactmentEcontract = new EnactmentEcontract();
        enactmentEcontract.setValidTrue();
        econtract.setEnactmentEcontract(enactmentEcontract);
    }
    
    protected void setManagementEcontract() throws Exception {
        ManagementEcontract managementEcontract = new ManagementEcontract();
        managementEcontract.setStatus(1);
        econtract.setManagementEcontract(managementEcontract);
    }
    
    protected Econtract getObject(){
        return this.econtract;
    }

}
