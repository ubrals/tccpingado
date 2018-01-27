package resources.databases.dao.api;

import contracts.Econtract;

public interface EcontractDaoInterface {
    /**
     * 
     * @param id long as Econtract id
     * @return {@link Econtract}
     */
	Econtract findEcontractById(long id);
	
	/**
	 * 
	 * @param econtract {@link Econtract}
	 */
	void insertEcontract(Econtract econtract);
}
