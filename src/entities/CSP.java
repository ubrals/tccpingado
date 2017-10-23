package entities;

import entities.values.Content;

public interface CSP {

	Content contents = null;

	public abstract void publishContent(Content content);

	public abstract void receiveContent(Content content);

	public abstract Content[] listContent();

	public abstract void deliverContent();

	public abstract void chargeDeliveredContent();

	public abstract void payDeliveredContent();

	public abstract void negotiatePrice();

}
