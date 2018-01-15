package entities;

import java.util.Collection;
import java.util.List;

import contracts.Econtract;
import contracts.Status;
import entities.values.Content;
import entities.values.ContentDelivered;

public interface CSP {

	Collection<Content> contents = null;

	public abstract void publishContent(Content content);

	public abstract void receiveContent(Content content);

	public abstract Collection<Content> listContents();

	public abstract ContentDelivered provisionContent(Content content, Party consumer, String password, String account) throws Exception;

    public abstract void chargeDeliveredContent(ContentDelivered contentDelivered) throws Exception;

    public abstract void setEcontractStatusDeliveredContent(ContentDelivered contentDeleivered, Status status) throws Exception;

    public abstract void deliverContent(ContentDelivered contentDelivered) throws Exception;

	public abstract void payDeliveredContent(Econtract econtract);

	public abstract void negotiatePrice();

}
