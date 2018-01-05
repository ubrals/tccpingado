package resources;

public class Wallet {

	private long balance;

	private String password;

	public Wallet(String password) {
        this.balance = 0l;
        this.password = password;
    }

    public long getBalance() {
	    return this.balance;
	}

	public void subtractCredits(long credits, byte key) {
	    this.balance=this.balance-credits;
	}

	public void addCredits(long credits, byte key) {
	    this.balance=this.balance+credits;
	}

    public void setPassword(String oldPassword, String newPassword) {
        if (password == oldPassword)
            password = newPassword;
    }

	public byte generateKey(long credits) {
		return 0;
	}

}
