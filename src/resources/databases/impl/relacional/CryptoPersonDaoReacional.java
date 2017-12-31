package resources.databases.impl.relacional;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Statement;

import entities.CryptoPerson;
import entities.Customer;
import entities.ISP;
import entities.Producer;
import entities.values.Content;
import entities.values.ExchangedValue;
import resources.databases.dao.api.CryptoPersonDaoInterface;

public class CryptoPersonDaoReacional implements CryptoPersonDaoInterface {
	private ConexaoInterface conexao;

	public CryptoPersonDaoReacional(ConexaoInterface conexao) {
		this.conexao = conexao;
	}

	@Override
	public CryptoPerson findCryptoPersonById(long id) {
		CryptoPerson cryptoPerson = (CryptoPerson) null;

        try{
	        Statement st;
	        st = conexao.getConnection().createStatement();
	        String sql = "select id , name , wallet , role from cryptoperson";
	        ResultSet resultados = st.executeQuery(sql);
	        
	        while(resultados.next()){
	            long cryptoPersonId = resultados.getLong("id");
	            String name = resultados.getString("name");
	            String wallet = resultados.getString("wallet");
	            String role = resultados.getString("role");
	            switch (role.toUpperCase()) {
				case "ISP":
					cryptoPerson = new ISP(cryptoPersonId, name);
					break;
				case "PRODUCER":
					cryptoPerson = new Producer(cryptoPersonId, name);
					break;
				case "CUSTOMER":
					cryptoPerson = new Customer(cryptoPersonId, name);
					break;
				default:
					cryptoPerson = (CryptoPerson) null;
					break;
				}
	        }
        }
	    catch(Exception ex){
	        ex.printStackTrace();
	    }
        return cryptoPerson;
	}
}
