package resources.databases.impl.relacional;


import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import contracts.Econtract;
import entities.ISP;
import entities.Producer;
import entities.values.Content;
import entities.values.ExchangedValue;
import resources.databases.dao.api.CryptoPersonDaoInterface;
import resources.databases.dao.api.EcontractDaoInterface;
import resources.databases.dao.api.ExchangedValueDaoInterface;
import resources.databases.impl.relacional.javadb.ConexaoJavaDbDerby;
import software.controllers.CtrlCryptoPerson;
import software.controllers.CtrlEcontract;

public class ExchangedValueDaoReacional implements ExchangedValueDaoInterface {
	private ConexaoInterface conexao;

	public ExchangedValueDaoReacional(ConexaoInterface conexao) {
		this.conexao = conexao;
	}

	@Override
	public List<Content> listContent() {
        List<Content> contents;
        contents = new ArrayList<>();
        
        ////
        CtrlCryptoPerson ctrl_crp = new CtrlCryptoPerson();
        ////
        CtrlEcontract ctrl_ect = new CtrlEcontract();
        
        try{
            Statement st;
            st = conexao.getConnection().createStatement();
            String sql = "select id, type, subType, value, econtractId, title, size, producerId, ispId, location from exchangedvalue";
            ResultSet resultados = st.executeQuery(sql);
            
            while(resultados.next()){
                long contentId = resultados.getLong("id");
                String type = resultados.getString("type");
                String subType = resultados.getString("subType");
                String value = resultados.getString("value");
                long econtractId = resultados.getLong("econtractId");
                
                String title = resultados.getString("title");
                long size = resultados.getLong("size");
                long producerId = resultados.getLong("producerId");
                
                long ispId = resultados.getLong("ispId");
                String location = resultados.getString("location");
                String filename = resultados.getString("filename");
                
                ExchangedValue content;
                Econtract econtract = ctrl_ect.findEcontractById(econtractId);
                Producer producer = (Producer) ctrl_crp.findCryptoPersonById(producerId);
                ISP isp = (ISP) ctrl_crp.findCryptoPersonById(ispId);
                
//                content = new Content("video", null, ectPrdXisp, "PvsNP", 17000000l, new byte[]{ 0 }, producer, isp);
                content = new Content(type, subType, value, econtract, title, size, new byte[]{ 0 }, producer, isp, location, filename);
                contents.add((Content) content);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return contents;
	}

	@Override
	public Content findByContentId(long id) {
		Content content = (Content) null;
		
        ////
        CryptoPersonDaoInterface crp_dao;
        crp_dao = new CryptoPersonDaoReacional(conexao);
        ////
        EcontractDaoInterface ect_dao;
        ect_dao = new EcontractDaoReacional(conexao);

        try{
            Statement st;
            st = conexao.getConnection().createStatement();
            String sql = "select id, type, subType, value, econtractId, title, size, producerId, ispId, location from exchangedvalue where id = " + id;
            ResultSet resultados = st.executeQuery(sql);
            
	        if(resultados.getFetchSize() > 1)
	        	throw new Exception("..:ERR:More than one ExchangedValue was found. Contact sysadmin!");
	        
            while(resultados.next()){
                long contentId = resultados.getLong("id");
                String type = resultados.getString("type");
                String subType = resultados.getString("subType");
                String value = resultados.getString("value");
                long econtractId = resultados.getLong("econtractId");
                
                String title = resultados.getString("title");
                long size = resultados.getLong("size");
                long producerId = resultados.getLong("producerId");
                
                long ispId = resultados.getLong("ispId");
                String location = resultados.getString("location");
                String filename = resultados.getString("filename");

                Econtract econtract = ect_dao.findEcontractById(econtractId);
                Producer producer = (Producer) crp_dao.findCryptoPersonById(producerId);
                ISP isp = (ISP) crp_dao.findCryptoPersonById(ispId);
                
//                content = new Content("video", null, ectPrdXisp, "PvsNP", 17000000l, new byte[]{ 0 }, producer, isp);
                content = new Content(type, subType, value, econtract, title, size, new byte[]{ 0 }, producer, isp, location, filename);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        
        return content;
	}
	
}
