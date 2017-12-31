package software.controllers;

import java.util.Collection;

import contracts.Econtract;
import entities.ISP;
import entities.Party;

public class CtrlEcontract {
	
	public Econtract newEcontract(long contentId, long customerId, long ispId){
		Econtract econtract = new Econtract();
        Collection<Party> parties;
//        parties.add(e);
//        setParty(party);

//		econtract.setParty(party);
		return econtract;
	}
	
}
