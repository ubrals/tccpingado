package entities;

import java.util.ArrayList;
import java.util.Collection;
import entities.values.Content;

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
	    Content list[] = new Content[this.content.size()];
	    int i=0;
	    
	    for(Content cont : content)
	        list[i++] = (Content) content;
	    
	    return list;
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
