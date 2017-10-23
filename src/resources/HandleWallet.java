package resources;

public interface HandleWallet {

	public long getBalance();

	public void subtractCredits(long credits);

	public void addCredits(long credits, byte key);

	public void setPassword(String oldPassword, String newPassword);

	/**
	 *  
	 */
	public byte generateKey(long credits);

}
