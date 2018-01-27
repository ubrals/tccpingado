package contracts;

import entities.Party;
import entities.values.Content;
import pricing.Framework;

public class EcontractDirector {
    
    private EcontractBuilder builder;
    
    public EcontractDirector(){
    }
    
    /**
     * Start to prepare the building of the Econtract
     */
    public void prepare(){
        this.builder = new EcontractBuilder();
    }
    
    /**
     * 
     * @param id long as Econtract id
     * @return
     */
    public Econtract newCleanContract(long id){
        this.builder = new EcontractBuilder();
        Econtract econtract = new Econtract();
        econtract.setId(id);
        return econtract;
    }
    
    /**
     * 
     * @param content {@link Content}
     * @param provider {@link Party}
     * @param consumer {@link Party}
     * @param framework {@link Framework}
     * @param frameworkReference String as reference to calculate price
     * @param frameworkPrice double as price
     * @param fractionMicro int as time frame
     * @throws Exception
     */
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
    
    /**
     * 
     * @param econtractId long as Econtract id
     * @param content {@link Content}
     * @param provider {@link Party}
     * @param consumer {@link Party}
     * @param framework {@link Framework}
     * @param frameworkReference String as reference to calculate price
     * @param frameworkPrice double as price
     * @param fractionMicro int as time frame
     * @param jitTimeToStart long as timestamp setup by System.currentTimeMillis()
     * @param valid int as validity of Econtract
     * @param status int as status of Econtract
     * @throws Exception
     */
    public void buildExistentEcontract(long econtractId, Content content, Party provider, Party consumer, Framework framework, String frameworkReference, double frameworkPrice, int fractionMicro, long jitTimeToStart, int valid, int status) throws Exception {
        builder.buildEcontract();
        builder.setEcontractId(econtractId);
        builder.setParties(provider, consumer);
        builder.setExchangedValue(content);
        builder.setFramework(framework, frameworkReference, frameworkPrice);
        builder.setMicroEcontract(fractionMicro);
        builder.setJustintimeEcontract(jitTimeToStart);
        builder.setEnactmentEcontract(valid);
        builder.setManagementEcontract(status);
    }
    
    /**
     * 
     * @return {@link Econtract} built
     */
    public Econtract getObject(){
        return builder.getObject();
    }
    
    /**
     * 
     * @param econtract {@link Econtract}
     * @param status {@link Status}
     * @throws Exception
     */
    public void setEcontractStatus(Econtract econtract, Status status) throws Exception{
        econtract.getManagementEcontract().setStatus(status);
    }

}
