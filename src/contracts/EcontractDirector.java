package contracts;

import java.util.ArrayList;
import java.util.Collection;

import entities.Party;
import entities.values.Content;
import pricing.Framework;
import pricing.components.Time;

public class EcontractDirector {
    
    EcontractBuilder builder;
    
    public EcontractDirector(){
    }
    
    public void prepare(){
        this.builder = new EcontractBuilder();
    }
    
    public void build(Content content, Party provider, Party consumer, Framework framework, String frameworkValue, int fractionMicro) throws Exception {
        builder.setParties(provider, consumer);
        builder.setExchangedValue(content);
        builder.setFramework(framework, frameworkValue);
        builder.setMicroEcontract(fractionMicro);
        builder.setJustintimeEcontract();
        builder.setEnactmentEcontract();
        builder.setManagementEcontract();
    }
    
    public Econtract getObject(){
        return builder.getObject();
    }
}
