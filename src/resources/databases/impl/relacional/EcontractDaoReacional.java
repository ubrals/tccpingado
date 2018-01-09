package resources.databases.impl.relacional;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import contracts.Econtract;
import contracts.EcontractDirector;
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
import pricing.Framework;
import pricing.components.Component;
import pricing.components.Time;
import pricing.components.TimeShares;
import resources.databases.dao.api.CryptoPersonDaoInterface;
import resources.databases.dao.api.EcontractDaoInterface;
import resources.databases.dao.api.ExchangedValueDaoInterface;
import software.controllers.CtrlCryptoPerson;
import software.controllers.CtrlEcontract;
import software.controllers.CtrlExchangedValue;

public class EcontractDaoReacional implements EcontractDaoInterface {
	private ConexaoInterface conexao;
	

	public EcontractDaoReacional(ConexaoInterface conexao) {
		this.conexao = conexao;
	}

	@Override
	public Econtract findEcontractById(long id) {
	    EcontractDirector ec_director = new EcontractDirector();
		Econtract econtract = (Econtract) null;
		
		///
        CtrlExchangedValue ctrl_exv;
        ctrl_exv = new CtrlExchangedValue();
        ////
        CtrlCryptoPerson ctrl_crp;
        ctrl_crp = new CtrlCryptoPerson();
        
		try{
	        Statement st;
	        st = conexao.getConnection().createStatement();
	        String sql = "select ID, CONTENTID, PARTYID1, PARTYID2, MICROFRACTION, JITTIMETOSTART, ENACTMENTVALID, MANAGEMENTSTATUS, FRAMEWORK, FRAMEWORK_VALUE from econtract where ID = " + id;
	        ResultSet resultados = st.executeQuery(sql);
	        
	        if(resultados.getFetchSize() > 1)
	        	throw new Exception("..:ERR:More than one Econtract was found. Contact sysadmin!");
	        
	        while(resultados.next()){
	            long econtractId = resultados.getLong("ID");
	            long contentId = resultados.getLong("CONTENTID");
	            long partyId1 = resultados.getLong("PARTYID1");
	            long partyId2 = resultados.getLong("PARTYID2");
	            int microFraction = resultados.getInt("MICROFRACTION");
	            long jitTimeToStart = resultados.getLong("JITTIMETOSTART");
	            int enactmentValid = resultados.getInt("ENACTMENTVALID");
	            int managementStatus = resultados.getInt("MANAGEMENTSTATUS");
	            String frameworkComponent = resultados.getString("FRAMEWORK");
	            String frameworkValue = resultados.getString("FRAMEWORK_VALUE");
	            
	            Content content = ctrl_exv.findByContentId(contentId);
                Party provider = ctrl_crp.findCryptoPersonById(partyId1);
                Party consumer = ctrl_crp.findCryptoPersonById(partyId2);
                
                Framework framework = null;
                    switch(frameworkComponent.toUpperCase()){
                        case "TIME" : framework = new Time(frameworkValue.toUpperCase());
                    }
	            
	            ec_director.prepare();
	            ec_director.buildExistentEcontract(econtractId, content, provider, consumer, framework, frameworkValue, microFraction, jitTimeToStart, enactmentValid, managementStatus);
	            econtract = ec_director.getObject();
	        }
        }
	    catch(Exception ex){
	        ex.printStackTrace();
	    }
        return econtract;
	}

	@Override
	public void insertEcontract(Econtract econtract) {

		try{
	        Statement st;
	        st = conexao.getConnection().createStatement();
	        String sql = "insert into econtract values (";
	        sql += econtract.getId() + ", ";
            sql += ((Content)econtract.getExchangedValue()).getId() + ", ";
            {
	        	for(Party party : econtract.getParty()){
                    sql += ((CryptoPerson)party).getId() + ", ";
	        	}
	        }
            sql += econtract.getMicroEcontract().getFraction() + ", ";
            sql += "'" + econtract.getJustintimeEcontract().getTimeToStartLong() + "', ";
            sql += econtract.getEnactmentEcontract().isValidInt() + ", ";
            sql += econtract.getManagementEcontract().getStatus() + ", ";
            sql += "'" + ((Component)econtract.getFramework()).getLabel() + "', ";
            sql += "'" + ((Component)econtract.getFramework()).getValue() + "')";
            System.out.println(sql);
	        
            try {
                st.execute(sql);
                if(st.getUpdateCount() == 1){
                    System.out.println("Feito");
                }
                else{
                    System.out.println("..:WRN:There has been inserted" + st.getUpdateCount() + "econtracts. Please contact sysadmin");
                }
            } catch (Exception e) {
                e.printStackTrace();
	        }
        }
	    catch(Exception ex){
	        ex.printStackTrace();
	    }
		
	}

}
