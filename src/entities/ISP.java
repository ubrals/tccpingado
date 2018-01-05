package entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import contracts.Econtract;
import entities.values.Content;
import resources.databases.dao.api.ExchangedValueDaoInterface;
import resources.databases.impl.relacional.ExchangedValueDaoReacional;
import resources.databases.impl.relacional.javadb.ConexaoDerbyDefault;
import software.controllers.CtrlEcontract;
import software.controllers.CtrlExchangedValue;

public class ISP extends CryptoPerson implements CSP {

    private Collection<Content> content = new ArrayList<Content>();

    public ISP(long id, String name) {
        super(id, name);
        // TODO Auto-generated constructor stub
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
	 * @see entities.CSP#deliverContent()
	 */
	public void deliverContent(Content content, Party consumer) {
		CtrlEcontract ctrl_ect = new CtrlEcontract();
		CtrlExchangedValue ctrl_exv = new CtrlExchangedValue();
		Econtract econtract = ctrl_ect.newEcontract(content, content.getISP(), consumer);
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
