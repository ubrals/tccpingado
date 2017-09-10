package entities;

import resources.HandleWallet;
import resources.Wallet;

public abstract class CryptoPerson extends Party implements HandleWallet {
    
    private Wallet wallet;

    public CryptoPerson() {
        // TODO Auto-generated constructor stub
    }

}
