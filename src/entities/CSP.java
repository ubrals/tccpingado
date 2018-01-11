package entities;

import java.util.Collection;
import java.util.List;

import contracts.Econtract;
import entities.values.Content;
import entities.values.ContentDelivered;

public interface CSP {

	Collection<Content> contents = null;

	public abstract void publishContent(Content content);

	public abstract void receiveContent(Content content);

	public abstract Collection<Content> listContent();

	public abstract ContentDelivered deliverContent(Content content, Party consumer) throws Exception;

	public abstract void chargeDeliveredContent(ContentDelivered contentDeleivered) throws Exception;

	public abstract void payDeliveredContent(Econtract econtract);

	public abstract void negotiatePrice();

}
