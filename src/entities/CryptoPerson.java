package entities;

import resources.HandleWallet;
import resources.Wallet;

public class CryptoPerson extends Party implements HandleWallet {

	public CryptoPerson(long id, String name) {
        super(id, name);
        // TODO Auto-generated constructor stub
    }


    private Wallet wallet;


	public Wallet getWallet() {
        return wallet;
    }


    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }


    /**
	 * @see resources.HandleWallet#getBalance()
	 */
	public long getBalance() {
		return wallet.getBalance();
	}


	/**
	 * @see resources.HandleWallet#subtractCredits(long)
	 */
	public void subtractCredits(long credits) {
        this.wallet.subtractCredits(credits, (byte) 0);
	}


	/**
	 * @see resources.HandleWallet#addCredits(long, byte)
	 */
	public void addCredits(long credits, byte key) {
	    this.wallet.addCredits(credits, key);
	}


	/**
	 * @see resources.HandleWallet#setPassword(java.lang.String, java.lang.String)
	 */
	public void setPassword(String oldPassword, String newPassword) {
	    
	}


	/**
	 * @see resources.HandleWallet#generateKey(long)
	 * 
	 *  
	 */
	public byte generateKey(long credits) {
		return 0;
	}

}
