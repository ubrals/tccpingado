package entities;

import resources.HandleWallet;
import resources.Wallet;

public class CryptoPerson extends Party implements HandleWallet {

    private Wallet wallet;
    private String account;

    /**
     * 
     * @param id long as Party id
     * @param name String
     * @param password String as password for account/wallet
     * @param account String as account identification
     */
	public CryptoPerson(long id, String name, String password, String account) {
        super(id, name);
        this.account = account;
        wallet = new Wallet(password);
    }

	/**
	 * 
	 * @return wallet {@link Wallet}
	 */
	public Wallet getWallet() {
        return wallet;
    }

	/**
	 * 
	 * @param wallet {@link Wallet}
	 */
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
	    this.wallet.setPassword(oldPassword, newPassword);
	}

	/**
	 * 
	 * @return account String as account identification
	 */
	public String getAccount() {
        return account;
    }

	/**
	 * 
	 * @param account String as account identification
	 */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
	 * @see resources.HandleWallet#generateKey(long)
	 */
	public byte generateKey(long credits) {
		return 0;
	}

}
