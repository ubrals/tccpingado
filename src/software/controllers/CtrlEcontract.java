package software.controllers;

import java.util.ArrayList;
import java.util.Collection;

import contracts.Econtract;
import contracts.EnactmentEcontract;
import contracts.JustintimeEcontract;
import contracts.ManagementEcontract;
import contracts.MicroEcontract;
import entities.ISP;
import entities.Party;
import entities.values.Content;
import resources.databases.dao.api.EcontractDaoInterface;
import resources.databases.impl.relacional.EcontractDaoReacional;
import resources.databases.impl.relacional.javadb.ConexaoDerbyDefault;

public class CtrlEcontract implements EcontractDaoInterface {
	
	public Econtract newEcontract(Content content, Party provider, Party consumer){
		Econtract econtract = new Econtract();
        Collection<Party> parties = new ArrayList<>();
        parties.add(provider);
        parties.add(consumer);
        econtract.setParty(parties);
        econtract.setExchangedValue(content);
        ////
        MicroEcontract microEcontract = new MicroEcontract(60);
        econtract.setMicroEcontract(new MicroEcontract(60));
        ////
        JustintimeEcontract justintimeEcontract = new JustintimeEcontract();
        justintimeEcontract.setTimeToStartLong(20180103210000l);
        econtract.setJustintimeEcontract(justintimeEcontract);
        ////
        EnactmentEcontract enactmentEcontract = new EnactmentEcontract();
        enactmentEcontract.setValidTrue();
        econtract.setEnactmentEcontract(enactmentEcontract);
        ////
        ManagementEcontract managementEcontract = new ManagementEcontract();
        managementEcontract.setStatus(1);
        econtract.setManagementEcontract(managementEcontract);

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
