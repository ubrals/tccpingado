package resources.databases.dao.api;

import entities.CryptoPerson;

public interface CryptoPersonDaoInterface {
    /**
     * 
     * @param id long as CryptoPerson id
     * @return {@link CryptoPerson}
     */
	CryptoPerson findCryptoPersonById(long id);
}
