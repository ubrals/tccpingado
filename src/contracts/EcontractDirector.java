package contracts;

import entities.Party;
import entities.values.Content;
import pricing.Framework;

public class EcontractDirector {
    
    private EcontractBuilder builder;
    
    public EcontractDirector(){
    }
    
    public void prepare(){
        this.builder = new EcontractBuilder();
    }
    
    public Econtract newCleanContract(long id){
        this.builder = new EcontractBuilder();
        Econtract econtract = new Econtract();
        econtract.setId(id);
        return econtract;
    }
    
    public void buildNewEcontract(Content content, Party provider, Party consumer, Framework framework, String frameworkReference, double frameworkPrice, int fractionMicro) throws Exception {
        builder.buildEcontract();
        builder.setParties(provider, consumer);
        builder.setExchangedValue(content);
        builder.setFramework(framework, frameworkReference, frameworkPrice);
        builder.setMicroEcontract(fractionMicro);
        builder.setJustintimeEcontract();
        builder.setEnactmentEcontract();
        builder.setManagementEcontract();
    }
    
    public void buildExistentEcontract(long econtractId, Content content, Party provider, Party consumer, Framework framework, String frameworkReference, double frameworkPrice, int microFraction, long jitTimeToStart, int valid, int status) throws Exception {
        builder.buildEcontract();
        builder.setEcontractId(econtractId);
        builder.setParties(provider, consumer);
        builder.setExchangedValue(content);
        builder.setFramework(framework, frameworkReference, frameworkPrice);
        builder.setMicroEcontract(microFraction);
        builder.setJustintimeEcontract(jitTimeToStart);
        builder.setEnactmentEcontract(valid);
        builder.setManagementEcontract(status);
    }
    
    public Econtract getObject(){
        return builder.getObject();
    }
}
