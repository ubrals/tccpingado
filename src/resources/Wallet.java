package resources;

import entities.CryptoPerson;

public class Wallet {

	private long balance;

	private String password;

	private CryptoPerson cryptoPerson;

	public long getBalance() {
	    return balance;
	}

	public void subtractCredits(long credits, byte key) {
	    balance=balance-credits;
	}

	public void addCredits(long credits, byte key) {
	    balance=balance+credits;
	}

    public void setPassword(String oldPassword, String newPassword) {
        if (password == oldPassword)
            password = newPassword;
    }

	public byte generateKey(long credits) {
		return 0;
	}

    public Wallet(String password) {
        this.balance = 0l;
        this.password = password;
    }

}
