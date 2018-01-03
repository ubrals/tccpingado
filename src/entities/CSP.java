package entities;

import java.util.Collection;
import java.util.List;

import entities.values.Content;

public interface CSP {

	Collection<Content> contents = null;

	public abstract void publishContent(Content content);

	public abstract void receiveContent(Content content);

	public abstract Collection<Content> listContent();

	public abstract void deliverContent(Content content, Party consumer);

	public abstract void chargeDeliveredContent();

	public abstract void payDeliveredContent();

	public abstract void negotiatePrice();

}
