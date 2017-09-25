package entities;

import resources.HandleWallet;
import resources.Wallet;

public abstract class CryptoPerson extends Party implements HandleWallet {
    
    private Wallet wallet;
    private HandleWallet handleWallet;
    
    public CryptoPerson(long id, String name, String walletPassword) {
        super(id, name);
        wallet = new Wallet(walletPassword);
    }

    @Override
    public long getBalance() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void subtractBalance(long balance) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void addBalance(long balance) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void generateKey() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String sendKey() {
        // TODO Auto-generated method stub
        return null;
    }
}
