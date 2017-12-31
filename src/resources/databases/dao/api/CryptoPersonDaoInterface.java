package resources.databases.dao.api;

import entities.CryptoPerson;

public interface CryptoPersonDaoInterface {
	public CryptoPerson findCryptoPersonById(long id);
}
