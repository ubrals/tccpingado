package entities;

import java.util.Collection;
import java.util.List;

import contracts.Econtract;
import entities.values.Content;
import entities.values.ContentDeleivered;

public interface CSP {

	Collection<Content> contents = null;

	public abstract void publishContent(Content content);

	public abstract void receiveContent(Content content);

	public abstract Collection<Content> listContent();

	public abstract ContentDeleivered deliverContent(Content content, Party consumer) throws Exception;

	public abstract void chargeDeliveredContent(ContentDeleivered contentDeleivered) throws Exception;

	public abstract void payDeliveredContent(Econtract econtract);

	public abstract void negotiatePrice();

}
