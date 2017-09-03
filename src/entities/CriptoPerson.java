package entities;

import resources.HandleWallet;
import resources.Wallet;

public abstract class CriptoPerson extends Party implements HandleWallet {
    
    private Wallet wallet;

    public CriptoPerson() {
        // TODO Auto-generated constructor stub
    }

}
