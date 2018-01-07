package resources.databases.impl.relacional;


import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
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
	public Collection<Content> listContent() {
        List<Content> contents;
        contents = new ArrayList<>();
        
        ////
        CtrlCryptoPerson ctrl_crp = new CtrlCryptoPerson();
        ////
        CtrlEcontract ctrl_ect = new CtrlEcontract();
        
        try{
            Statement st;
            st = conexao.getConnection().createStatement();
            
//            ResultSet show = st.executeQuery("select * from sys.SYSTABLES");
//            while(show.next()){
//            	System.out.println(show.getString("TABLENAME"));
//            }
//            System.out.println(" >>" + conexao.getConnection().getMetaData().getURL() + 
// 				   "." + conexao.getConnection().getMetaData().getUserName() + 
// 				   "." + conexao.getConnection().getSchema() + 
// 				   "." + conexao.getConnection().getMetaData().getIdentifierQuoteString()
// 				   );

//            String sql = "select id, type, subType, value, econtractId, title, size, producerId, ispId, location, filename from exchangedvalue";
            String sql = "select * from exchangedvalue";
            ResultSet resultados = st.executeQuery(sql);
            
            while(resultados.next()){
                long contentId = resultados.getLong("ID");
                String type = resultados.getString("TYPE");
                String subType = resultados.getString("SUBTYPE");
                String value = resultados.getString("VALUE");
                long econtractId = resultados.getLong("ECONTRACTID");
                
                String title = resultados.getString("TITLE");
                long size = resultados.getLong("SIZE");
                long producerId = resultados.getLong("PRODUCERID");
                
                long ispId = resultados.getLong("ISPID");
                String location = resultados.getString("LOCATION");
                String filename = resultados.getString("FILENAME");
                
                ExchangedValue content;
                Econtract econtract = ctrl_ect.findEcontractById(econtractId);
                Producer producer = (Producer) ctrl_crp.findCryptoPersonById(producerId);
                ISP isp = (ISP) ctrl_crp.findCryptoPersonById(ispId);
//                Econtract econtract = new Econtract();
//                Producer producer = new Producer(999, "Produtor");
//                ISP isp = new ISP(888, "Internet provedor");
                
//                content = new Content("video", null, ectPrdXisp, "PvsNP", 17000000l, new byte[]{ 0 }, producer, isp);
                content = new Content(type, subType, value, econtract, title, size, new byte[]{ 0 }, producer, isp, location, filename);
                ((Content)content).setId(contentId);
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
        CtrlCryptoPerson ctrl_crp;
        ctrl_crp = new CtrlCryptoPerson();
        ////
        CtrlEcontract ctrl_ect;
        ctrl_ect = new CtrlEcontract();

        try{
            Statement st;
            st = conexao.getConnection().createStatement();
            String sql = "select id, type, subType, value, econtractId, title, size, producerId, ispId, location, filename from exchangedvalue where id = " + id;
            ResultSet resultados = st.executeQuery(sql);
            
	        if(resultados.getFetchSize() > 1)
	        	throw new Exception("..:ERR:More than one ExchangedValue was found. Contact sysadmin!");
	        
            while(resultados.next()){
                long contentId = resultados.getLong("ID");
                String type = resultados.getString("TYPE");
                String subType = resultados.getString("SUBTYPE");
                String value = resultados.getString("VALUE");
                long econtractId = resultados.getLong("ECONTRACTID");
                
                String title = resultados.getString("TITLE");
                long size = resultados.getLong("SIZE");
                long producerId = resultados.getLong("PRODUCERID");
                
                long ispId = resultados.getLong("ISPID");
                String location = resultados.getString("LOCATION");
                String filename = resultados.getString("FILENAME");

                Econtract econtract = ctrl_ect.findEcontractById(econtractId);
                Producer producer = (Producer) ctrl_crp.findCryptoPersonById(producerId);
                ISP isp = (ISP) ctrl_crp.findCryptoPersonById(ispId);
//                Econtract econtract = new Econtract();
//                Producer producer = new Producer(999, "Produtor");
//                ISP isp = new ISP(888, "Internet");
                
//                content = new Content("video", null, ectPrdXisp, "PvsNP", 17000000l, new byte[]{ 0 }, producer, isp);
                content = new Content(type, subType, value, econtract, title, size, new byte[]{ 0 }, producer, isp, location, filename);
                content.setId(contentId);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        
        return content;
	}
	
}
