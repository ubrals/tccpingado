package entities;

import java.util.Collection;
import java.util.List;

import contracts.Econtract;
import entities.values.Content;

public interface CSP {

	Collection<Content> contents = null;

	public abstract void publishContent(Content content);

	public abstract void receiveContent(Content content);

	public abstract Collection<Content> listContent();

	public abstract void deliverContent(Content content, Party consumer);

	public abstract void chargeDeliveredContent(Econtract econtract);

	public abstract void payDeliveredContent(Econtract econtract);

	public abstract void negotiatePrice();

}
