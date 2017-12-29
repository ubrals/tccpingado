package software.controllers;

import entities.CryptoPerson;
import entities.Customer;
import entities.Party;

public class CtrlCryptoPerson {
	
	public CryptoPerson findCryptoPersonById(long partyId){
		return new Customer(partyId, "null");
	}
}
