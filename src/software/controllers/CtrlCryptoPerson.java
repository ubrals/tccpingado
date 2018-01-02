package software.controllers;

import entities.CryptoPerson;
import entities.Customer;
import entities.Party;
import resources.databases.dao.api.CryptoPersonDaoInterface;
import resources.databases.impl.relacional.ConexaoInterface;
import resources.databases.impl.relacional.CryptoPersonDaoReacional;
import resources.databases.impl.relacional.javadb.ConexaoJavaDbDerby;

public class CtrlCryptoPerson {
	
	public CryptoPerson findCryptoPersonById(long partyId){
		ConexaoInterface conexao = new ConexaoJavaDbDerby("", "", "127.0.0.1", 1527, "tccpingado");
		
		CryptoPersonDaoInterface crp_dao = new CryptoPersonDaoReacional(conexao);
		return crp_dao.findCryptoPersonById(partyId);
	}
}
