package software.controllers;

import java.util.ArrayList;
import java.util.Collection;

import contracts.Econtract;
import contracts.EcontractDirector;
import contracts.EnactmentEcontract;
import contracts.JustintimeEcontract;
import contracts.ManagementEcontract;
import contracts.MicroEcontract;
import entities.ISP;
import entities.Party;
import entities.values.Content;
import pricing.Framework;
import resources.databases.dao.api.EcontractDaoInterface;
import resources.databases.dao.api.ExchangedValueDaoInterface;
import resources.databases.impl.relacional.EcontractDaoReacional;
import resources.databases.impl.relacional.javadb.ConexaoDerbyDefault;

public class CtrlEcontract implements EcontractDaoInterface {
	
	public Econtract newEcontract(Content content, Party provider, Party consumer, Framework framework, String frameworkValue, int fractionMicro) throws Exception{
	    EcontractDirector ec_director = new EcontractDirector();
	    ec_director.prepare();
	    ec_director.build(content, provider, consumer, framework, frameworkValue, fractionMicro);
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
