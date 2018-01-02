package resources.databases.dao.api;

import contracts.Econtract;

public interface EcontractDaoInterface {
	Econtract findEcontractById(long id);
	void insertEcontract(Econtract econtract);
}
