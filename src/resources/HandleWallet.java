package resources;

public interface HandleWallet {

    /**
     * 
     * @return long as balance
     */
	public long getBalance();

	/**
	 * 
	 * @param credits long
	 */
	public void subtractCredits(long credits);

	/**
	 * 
	 * @param credits long
	 * @param key String
	 */
	public void addCredits(long credits, byte key);

	/**
	 * 
	 * @param oldPassword String
	 * @param newPassword String
	 */
	public void setPassword(String oldPassword, String newPassword);

	/**
	 * 
	 * @param credits long as ammount to be added/subctracted
	 * @return key generated (not yet implemented)
	 */
	public byte generateKey(long credits);

}
