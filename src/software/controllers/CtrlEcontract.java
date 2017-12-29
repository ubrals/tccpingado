package software.controllers;

import contracts.Econtract;
import entities.ISP;

public class CtrlEcontract {
	
	public Econtract newEcontract(long contentId, long customerId, long ispId){
		Econtract econtract = new Econtract();
		
		econtract.setParty(party);
		return econtract;
	}
	
}
