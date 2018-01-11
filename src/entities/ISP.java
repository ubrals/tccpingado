package entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import contracts.Econtract;
import entities.values.Content;
import entities.values.ContentDelivered;
import pricing.Framework;
import pricing.components.Time;
import pricing.components.TimeShares;
import resources.databases.dao.api.ExchangedValueDaoInterface;
import resources.databases.impl.relacional.ExchangedValueDaoReacional;
import resources.databases.impl.relacional.javadb.ConexaoDerbyDefault;
import software.blockchain.PrepareData;
import software.controllers.CtrlEcontract;
import software.controllers.CtrlExchangedValue;

public class ISP extends CryptoPerson implements CSP {

    private Collection<Content> content = new ArrayList<Content>();

    public ISP(long id, String name) {
        super(id, name);
    }


	/**
	 * @see entities.CSP#publishContent(entities.values.Content)
	 */
	public void publishContent(Content content) {

	}


	/**
	 * @see entities.CSP#receiveContent(entities.values.Content)
	 */
	public void receiveContent(Content content) {
	    this.content.add(content);
	}


	/**
	 * @see entities.CSP#listContent()
	 */
	public Collection<Content> listContent() {
		CtrlExchangedValue ctrl_exv = new CtrlExchangedValue();
		this.content = ctrl_exv.listContent();
		
	    return content;
	}


	/**
	 * @return 
	 * @throws Exception 
	 * @see entities.CSP#deliverContent()
	 */
	public ContentDelivered deliverContent(Content content, Party consumer) throws Exception {
		CtrlEcontract ctrl_ect = new CtrlEcontract();
		
        Econtract econtract = ctrl_ect.findEcontractById(content.getId());
                  econtract = content.getEcontract();
        Party provider = null;
        int i = 0;
        for(Party party : econtract.getParty()){
            if(i == 0) provider = party;
        }
        long contentId = content.getId();
        long econtractId = econtract.getId();
        long consumerId = consumer.getId();
        Framework framework = content.getEcontract().getFramework();
        String frameworkReference = (String) framework.getReference();
        double frameworkPrice = framework.getPrice();
        int fractionMicro = econtract.getMicroEcontract().getFraction();
//        long jitTimeToStart = econtract.getJustintimeEcontract().getTimeToStartLong();
//        int valid = econtract.getEnactmentEcontract().isValidInt();
//        int status = econtract.getManagementEcontract().getStatus();
        CtrlExchangedValue ctrl_exv = new CtrlExchangedValue();
//		econtract = ctrl_ect.existentEcontract(econtractId, content, provider, consumer, framework, frameworkValue, fractionMicro, jitTimeToStart, valid, status);
		econtract = ctrl_ect.newEcontract(content, provider, consumer, framework, frameworkReference, frameworkPrice, fractionMicro);
		
        String contract_file = ctrl_exv.makeSymLink(econtract.getId(),
                                                    ((Content)econtract.getExchangedValue()).getLocation(),
                                                    ((Content)econtract.getExchangedValue()).getFilename());
        System.out.println(contract_file);
        String url = ctrl_exv.getURL("http://localhost", "/", contract_file);
        System.out.println(url);
        
        return new ContentDelivered(url, contentId, econtractId, consumerId, frameworkReference, frameworkPrice);
	}


	/**
	 * @see entities.CSP#chargeDeliveredContent()
	 */
	public void chargeDeliveredContent(ContentDelivered contentDelivered) throws Exception {
        PrepareData prepareData = new PrepareData();
        prepareData.getData(contentDelivered);
        
        long consumerId = contentDelivered.getConsumerId();
        long contentId = contentDelivered.getContentId();
        double debitAmount = contentDelivered.getDebitAmount();
        long econtractId = contentDelivered.getEcontractId();
        String timeReference = contentDelivered.getTimeReference();
        String url = contentDelivered.getUrl();
	    
	    Process sendProcess = Runtime.getRuntime().exec("java -jar dist/SendDebit.jar " 
	                                                + "localhost " 
	                                                + "8888 " 
	                                                + consumerId + " " 
	                                                + debitAmount);
	    if(sendProcess.exitValue() != 0)
	        ;
	}


	/**
	 * @see entities.CSP#payDeliveredContent()
	 */
	public void payDeliveredContent(Econtract econtract) {

	}


	/**
	 * @see entities.CSP#negotiatePrice()
	 */
	public void negotiatePrice() {

	}

}
