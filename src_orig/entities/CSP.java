package entities;

import java.util.ArrayList;
import java.util.List;

import entities.values.Content;
import resources.HandleWallet;

public interface CSP {
    
    List contents = new ArrayList<Content>();
    
    public void publishContent(Content content) throws Exception;

    public void receiveContent(Content content) throws Exception;

    public void listContent() throws Exception;

    public void deliverContent() throws Exception;

    public void chargeDeliveredContent() throws Exception;

    public void payDeliveredContent() throws Exception;

}
