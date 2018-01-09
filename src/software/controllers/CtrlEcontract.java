package software.controllers;


import contracts.Econtract;
import contracts.EcontractDirector;
import entities.Party;
import entities.values.Content;
import pricing.Framework;
import resources.databases.dao.api.EcontractDaoInterface;
import resources.databases.impl.relacional.EcontractDaoReacional;
import resources.databases.impl.relacional.javadb.ConexaoDerbyDefault;

public class CtrlEcontract implements EcontractDaoInterface {
	
    public Econtract newEcontract(Content content, Party provider, Party consumer, Framework framework, String frameworkValue, int fractionMicro) throws Exception{
        EcontractDirector ec_director = new EcontractDirector();
        ec_director.prepare();
        ec_director.buildNewEcontract(content, provider, consumer, framework, frameworkValue, fractionMicro);
        Econtract econtract = ec_director.getObject();
        
        insertEcontract(econtract);
        
        return econtract;
    }

    public Econtract existentEcontract(long econtractId, Content content, Party provider, Party consumer, Framework framework, String frameworkValue, int fractionMicro, long jitTimeToStart, int valid, int status) throws Exception{
        EcontractDirector ec_director = new EcontractDirector();
        ec_director.prepare();
        ec_director.buildExistentEcontract(econtractId, content, provider, consumer, framework, frameworkValue, fractionMicro, jitTimeToStart, valid, status);
        Econtract econtract = ec_director.getObject();
        
        insertEcontract(econtract);
        
        return econtract;
    }

	@Override
	public Econtract findEcontractById(long id) {
        EcontractDaoInterface ect_dao = new EcontractDaoReacional(ConexaoDerbyDefault.defaultConection());
      return ect_dao.findEcontractById(id);
	}

	@Override
	public void insertEcontract(Econtract econtract) {
		EcontractDaoInterface ect_dao = new EcontractDaoReacional(ConexaoDerbyDefault.defaultConection());
		ect_dao.insertEcontract(econtract);
	}
	
}
