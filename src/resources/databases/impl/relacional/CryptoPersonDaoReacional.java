package resources.databases.impl.relacional;

import java.sql.ResultSet;
import java.sql.Statement;

import entities.CryptoPerson;
import entities.Customer;
import entities.ISP;
import entities.Producer;
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
	        String sql = "select * from cryptoperson where id = " + id;
	        ResultSet resultados = st.executeQuery(sql);
	        
	        while(resultados.next()){
	            long cryptoPersonId = resultados.getLong("id");
	            String name = resultados.getString("name");
                String wallet = resultados.getString("wallet");
                String password = resultados.getString("password");
	            String role = resultados.getString("role");
	            String account = resultados.getString("account");
	            cryptoPerson = new CryptoPerson(cryptoPersonId, name, password, account);
	        }
        }
	    catch(Exception ex){
	        ex.printStackTrace();
	    }
        return cryptoPerson;
	}
}
