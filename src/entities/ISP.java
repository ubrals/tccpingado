package entities;

import resources.HandleWallet;
import resources.Wallet;

public abstract class ISP extends Party implements HandleWallet {
    
    private Wallet wallet;
    
    public ISP() {
        // TODO Auto-generated constructor stub
    }

    public abstract void publishContent();
    
    public abstract void receiveContent();
    
    public abstract void listContent();
    
    public abstract void deliverContent();
    
    public abstract void chargeDeliveredContent();
    
    public abstract void payDeliveredContent();
}
