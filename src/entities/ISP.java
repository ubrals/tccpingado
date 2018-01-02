package entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import entities.values.Content;
import resources.databases.dao.api.ExchangedValueDaoInterface;
import resources.databases.impl.relacional.ExchangedValueDaoReacional;
import resources.databases.impl.relacional.javadb.ConexaoDerbyDefault;

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
	public Content[] listContent() {
		ExchangedValueDaoInterface exv_dao = new ExchangedValueDaoReacional(ConexaoDerbyDefault.defaultConection());
		List<Content> list = exv_dao.listContent();
//	    Content list[] = new Content[this.content.size()];
	    int i=0;
	    this.content = list;
	    
	    return (Content[])list.toArray();
	}


	/**
	 * @see entities.CSP#deliverContent()
	 */
	public void deliverContent() {

	}


	/**
	 * @see entities.CSP#chargeDeliveredContent()
	 */
	public void chargeDeliveredContent() {

	}


	/**
	 * @see entities.CSP#payDeliveredContent()
	 */
	public void payDeliveredContent() {

	}


	/**
	 * @see entities.CSP#negotiatePrice()
	 */
	public void negotiatePrice() {

	}

}
