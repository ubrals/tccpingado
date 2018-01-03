package software.controllers;

import entities.CryptoPerson;
import entities.Customer;
import entities.Party;
import resources.databases.dao.api.CryptoPersonDaoInterface;
import resources.databases.impl.relacional.ConexaoInterface;
import resources.databases.impl.relacional.CryptoPersonDaoReacional;
import resources.databases.impl.relacional.javadb.ConexaoDerbyDefault;
import resources.databases.impl.relacional.javadb.ConexaoJavaDbDerby;

public class CtrlCryptoPerson {
	
	public CryptoPerson findCryptoPersonById(long partyId){
		CryptoPersonDaoInterface crp_dao = new CryptoPersonDaoReacional(ConexaoDerbyDefault.defaultConection());
		
		return crp_dao.findCryptoPersonById(partyId);
	}
}
