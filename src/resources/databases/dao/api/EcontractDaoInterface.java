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
	
	/**
	 * 
	 * @param econtract {@link Econtract}
	 * @param status int
	 * @throws Exception
	 */
	void setEcontractStatus(Econtract econtract, int status) throws Exception;
}
