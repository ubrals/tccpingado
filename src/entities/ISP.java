package entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import contracts.Econtract;
import entities.values.Content;
import pricing.Framework;
import pricing.components.Time;
import pricing.components.TimeShares;
import resources.databases.dao.api.ExchangedValueDaoInterface;
import resources.databases.impl.relacional.ExchangedValueDaoReacional;
import resources.databases.impl.relacional.javadb.ConexaoDerbyDefault;
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
	 * @throws Exception 
	 * @see entities.CSP#deliverContent()
	 */
	public void deliverContent(Content content, Party consumer) throws Exception {
		CtrlEcontract ctrl_ect = new CtrlEcontract();
		
        Econtract econtract = ctrl_ect.findEcontractById(content.getId());
                  econtract = content.getEcontract();
        long econtractId = content.getEcontract().getId();
        Party provider = null;
        int i = 0;
        for(Party party : econtract.getParty()){
            if(i == 0) provider = party;
        }
        Framework framework = content.getEcontract().getFramework();
        String frameworkValue = ((Time)framework).getValue();
        int fractionMicro = econtract.getMicroEcontract().getFraction();
//        long jitTimeToStart = econtract.getJustintimeEcontract().getTimeToStartLong();
//        int valid = econtract.getEnactmentEcontract().isValidInt();
//        int status = econtract.getManagementEcontract().getStatus();
        CtrlExchangedValue ctrl_exv = new CtrlExchangedValue();
//		econtract = ctrl_ect.existentEcontract(econtractId, content, provider, consumer, framework, frameworkValue, fractionMicro, jitTimeToStart, valid, status);
		econtract = ctrl_ect.newEcontract(content, provider, consumer, framework, frameworkValue, fractionMicro);
		
        String contract_file = ctrl_exv.makeSymLink(econtract.getId(),
                                                    ((Content)econtract.getExchangedValue()).getLocation(),
                                                    ((Content)econtract.getExchangedValue()).getFilename());
        System.out.println(contract_file);
        String url = ctrl_exv.getURL("http://localhost", "/", contract_file);
        System.out.println(url);
	}


	/**
	 * @see entities.CSP#chargeDeliveredContent()
	 */
	public void chargeDeliveredContent(Econtract econtract) {

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
