package resources;

public class Wallet {

	private long balance;

	private String password;

	/**
	 * 
	 * @param password String
	 */
	public Wallet(String password) {
        this.balance = 0l;
        this.password = password;
    }

	/**
	 * 
	 * @return balance long
	 */
    public long getBalance() {
	    return this.balance;
	}

    /**
     * 
     * @param credits long
     * @param key byte
     */
	public void subtractCredits(long credits, byte key) {
	    this.balance=this.balance-credits;
	}

	/**
	 * 
	 * @param credits long
	 * @param key nyte
	 */
	public void addCredits(long credits, byte key) {
	    this.balance=this.balance+credits;
	}

	/**
	 * 
	 * @param oldPassword String
	 * @param newPassword String
	 */
    public void setPassword(String oldPassword, String newPassword) {
        if (password == oldPassword)
            password = newPassword;
    }

    /**
     * 
     * @param credits long
     * @return byte
     */
	public byte generateKey(long credits) {
		return 0;
	}

}
