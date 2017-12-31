package resources.databases.impl.relacional;

import java.sql.ResultSet;
import java.sql.Statement;

import contracts.Econtract;
import entities.CryptoPerson;
import entities.Customer;
import entities.ISP;
import entities.Producer;
import entities.values.Product;
import resources.databases.dao.api.EcontractDaoInterface;

public class EcontractDaoReacional implements EcontractDaoInterface {
	private ConexaoInterface conexao;
	

	public EcontractDaoReacional(ConexaoInterface conexao) {
		this.conexao = conexao;
	}

	@Override
	public Econtract findEcontractById(long id) {
		Econtract econtract = (Econtract) null;
		try{
	        Statement st;
	        st = conexao.getConnection().createStatement();
	        String sql = "select id, contentId, partyId1, partyId2, microFraction, jitTimeToStart, enactmentValid, managementStatus from econtract";
	        ResultSet resultados = st.executeQuery(sql);
	        
	        while(resultados.next()){
	            long econtractId = resultados.getLong("id");
	            long contentId = resultados.getLong("contentId");
	            long partyId1 = resultados.getLong("partyId1");
	            long partyId2 = resultados.getLong("partyId2");
	            long microFraction = resultados.getLong("microFraction");
	            String jitTimeToStart = resultados.getString("jitTimeToStart");
	            long enactmentValid = resultados.getLong("enactmentValid");
	            long managementStatus = resultados.getLong("managementStatus");
	            econtract = new Econtract();
	            econtract.setId(econtractId);
	            
	            econtract.setExchangedValue(exchangedValue);
	            Product prd;
	            ((Product)econtract).setSubType(subType);
	        }
        }
	    catch(Exception ex){
	        ex.printStackTrace();
	    }
        return cryptoPerson;

	}

}
