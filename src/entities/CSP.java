package entities;

import java.util.Collection;
import java.util.List;

import contracts.Econtract;
import contracts.Status;
import entities.values.Content;
import entities.values.ContentDelivered;

public interface CSP {

	Collection<Content> contents = null;

	/**
	 * 
	 * @param content {@link Content}
	 */
	public abstract void publishContent(Content content);

	/**
	 * Not implemented
	 * @param content {@link Content}
	 */
	public abstract void receiveContent(Content content);

	/**
	 * 
	 * @return {@link Collection<Content>}
	 */
	public abstract Collection<Content> listContents();

	/**
	 * 
	 * @param content {@link Content}
	 * @param consumer {@link Party}
	 * @param password String as the password to unlock account/wallet
	 * @param account String as account identification
	 * @return {@link ContentDelivered}
	 * @throws Exception
	 */
	public abstract ContentDelivered provisionContent(Content content, Party consumer, String password, String account) throws Exception;

	/**
	 * 
	 * @param contentDelivered {@link ContentDelivered}
	 * @throws Exception
	 */
    public abstract void chargeDeliveredContent(ContentDelivered contentDelivered) throws Exception;

    /**
     * 
     * @param contentDeleivered {@link ContentDelivered}
     * @param status {@link Status}
     * @throws Exception
     */
    public abstract void setEcontractStatusDeliveredContent(ContentDelivered contentDeleivered, Status status) throws Exception;

    /**
     * 
     * @param contentDelivered {@link ContentDelivered}
     * @throws Exception
     */
    public abstract void deliverContent(ContentDelivered contentDelivered) throws Exception;

    /**
     * Not implemented
     * @param econtract {@link Econtract}
     */
	public abstract void payDeliveredContent(Econtract econtract);

	/**
	 * Not implemented
	 */
	public abstract void negotiatePrice();

}
