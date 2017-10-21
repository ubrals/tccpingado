package entities;

import entities.values.Content;
import resources.HandleWallet;
import resources.Wallet;

public abstract class ISP extends CryptoPerson implements CSP {

    public ISP(long id, String name, String walletPassword) {
        super(id, name, walletPassword);
    }

    @Override
    public void publishContent(Content content) throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void receiveContent(Content content) throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void listContent() throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deliverContent() throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void chargeDeliveredContent() throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void payDeliveredContent() throws Exception {
        // TODO Auto-generated method stub
        
    }

}
