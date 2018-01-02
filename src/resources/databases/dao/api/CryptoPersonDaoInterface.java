package resources.databases.dao.api;

import entities.CryptoPerson;

public interface CryptoPersonDaoInterface {
	CryptoPerson findCryptoPersonById(long id);
}
