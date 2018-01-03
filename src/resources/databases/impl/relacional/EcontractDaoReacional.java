package resources.databases.impl.relacional;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import contracts.Econtract;
import contracts.EnactmentEcontract;
import contracts.JustintimeEcontract;
import contracts.ManagementEcontract;
import contracts.MicroEcontract;
import entities.CryptoPerson;
import entities.Customer;
import entities.ISP;
import entities.Party;
import entities.Producer;
import entities.values.Content;
import entities.values.ExchangedValue;
import entities.values.Product;
import resources.databases.dao.api.CryptoPersonDaoInterface;
import resources.databases.dao.api.EcontractDaoInterface;
import resources.databases.dao.api.ExchangedValueDaoInterface;

public class EcontractDaoReacional implements EcontractDaoInterface {
	private ConexaoInterface conexao;
	

	public EcontractDaoReacional(ConexaoInterface conexao) {
		this.conexao = conexao;
	}

	@Override
	public Econtract findEcontractById(long id) {
		Econtract econtract = (Econtract) null;
		
        ExchangedValueDaoInterface exv_dao;
        exv_dao = new ExchangedValueDaoReacional(conexao);
        ////
        CryptoPersonDaoInterface crp_dao;
        crp_dao = new CryptoPersonDaoReacional(conexao);

		try{
	        Statement st;
	        st = conexao.getConnection().createStatement();
	        String sql = "select id, contentId, partyId1, partyId2, microFraction, jitTimeToStart, enactmentValid, managementStatus from econtract where id = " + id;
	        ResultSet resultados = st.executeQuery(sql);
	        
	        if(resultados.getFetchSize() > 1)
	        	throw new Exception("..:ERR:More than one Econtract was found. Contact sysadmin!");
	        
	        while(resultados.next()){
	            long econtractId = resultados.getLong("id");
	            long contentId = resultados.getLong("contentId");
	            long partyId1 = resultados.getLong("partyId1");
	            long partyId2 = resultados.getLong("partyId2");
	            long microFraction = resultados.getLong("microFraction");
	            long jitTimeToStart = resultados.getLong("jitTimeToStart");
	            int enactmentValid = resultados.getInt("enactmentValid");
	            int managementStatus = resultados.getInt("managementStatus");
	            
	            econtract = new Econtract();
	            econtract.setId(econtractId);
	            {
		            ExchangedValue exchangedValue = (Content)exv_dao.findByContentId(contentId);
		            econtract.setExchangedValue(exchangedValue);
	            }
	            {
		            Collection<Party> parties = new ArrayList<>();
		            Party party1 = crp_dao.findCryptoPersonById(partyId1);
		            Party party2 = crp_dao.findCryptoPersonById(partyId2);
		            parties.add(party1);
		            parties.add(party2);
		            econtract.setParty(parties);
	            }
	            {
	            	econtract.setMicroEcontract(new MicroEcontract(microFraction));
	            }
	            {
	            	JustintimeEcontract justintimeEcontract = new JustintimeEcontract();
	            	justintimeEcontract.setTimeToStartLong(jitTimeToStart);
	            	econtract.setJustintimeEcontract(justintimeEcontract);
	            }
	            {
	            	EnactmentEcontract enactmentEcontract = new EnactmentEcontract();
	            	if(enactmentValid == 1)
	            		enactmentEcontract.setValidTrue();
	            	else
	            		enactmentEcontract.setValidFalse();
	            	econtract.setEnactmentEcontract(enactmentEcontract);
	            }
	            {
	            	ManagementEcontract managementEcontract = new ManagementEcontract();
	            	managementEcontract.setStatus(managementStatus);
	            	econtract.setManagementEcontract(managementEcontract);
	            }
	        }
        }
	    catch(Exception ex){
	        ex.printStackTrace();
	    }
        return econtract;
	}

	@Override
	public void insertEcontract(Econtract econtract) {
        ExchangedValueDaoInterface exv_dao;
        exv_dao = new ExchangedValueDaoReacional(conexao);
        ////
        CryptoPersonDaoInterface crp_dao;
        crp_dao = new CryptoPersonDaoReacional(conexao);

		try{
	        Statement st;
	        st = conexao.getConnection().createStatement();
	        String sql = "insert into econtract values (";
	        sql += econtract.getId() + ", ";
        System.out.println(econtract.getId() + ", ");
            sql += ((Content)econtract.getExchangedValue()).getId() + ", ";
        System.out.println(((Content)econtract.getExchangedValue()).getId() + ", ");
            {
	        	for(Party party : econtract.getParty()){
                    sql += ((CryptoPerson)party).getId() + ", ";
        System.out.println(((CryptoPerson)party).getId() + ", ");
	        	}
	        }
            sql += econtract.getMicroEcontract().getFraction() + ", ";
        System.out.println(econtract.getMicroEcontract().getFraction() + ", ");
            sql += "'" + econtract.getJustintimeEcontract().getTimeToStartLong() + "', ";
        System.out.println("'" + econtract.getJustintimeEcontract().getTimeToStartLong() + "', ");
            sql += econtract.getEnactmentEcontract().isValidInt() + ", ";
        System.out.println(econtract.getEnactmentEcontract().isValidInt() + ", ");
            sql += econtract.getManagementEcontract().getStatus() + ")";
        System.out.println(econtract.getManagementEcontract().getStatus() + ")");
            System.out.println(sql);
	        
	        ResultSet resultados = null;
	        if(st.execute(sql)){
	            System.out.println("Feito");
//	        	resultados = st.getResultSet();
//	        	while(resultados.next()){
//    	        	for(int i=1; i<resultados.getMetaData().getColumnCount(); i++){
//    	        	    System.out.println(resultados.getString(i));
//    	        	}
//	        	}
	        }
	        
//	        if(resultados.getFetchSize() > 1)
//	        	throw new Exception("..:ERR:More than one Econtract was found. Contact sysadmin!");
	        
//	        while(resultados.next()){
//	            long econtractId = resultados.getLong("id");
//	            long contentId = resultados.getLong("contentId");
//	            long partyId1 = resultados.getLong("partyId1");
//	            long partyId2 = resultados.getLong("partyId2");
//	            long microFraction = resultados.getLong("microFraction");
//	            long jitTimeToStart = resultados.getLong("jitTimeToStart");
//	            int enactmentValid = resultados.getInt("enactmentValid");
//	            int managementStatus = resultados.getInt("managementStatus");
//	        }
        }
	    catch(Exception ex){
	        ex.printStackTrace();
	    }
		
	}

}
