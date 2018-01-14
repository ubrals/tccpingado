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
import contracts.Status;
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
            String sql = "select * from econtract where ID = " + id;
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
                String frameworkComponent = resultados.getString("FRAMEWORK").toUpperCase();
                String frameworkReference = resultados.getString("FRAMEWORK_REFERENCE").toUpperCase();
                double frameworkPrice = resultados.getDouble("FRAMEWORK_PRICE");

                Content content = ctrl_exv.findByContentId(contentId);
                Party provider = ctrl_crp.findCryptoPersonById(partyId1);
                Party consumer = ctrl_crp.findCryptoPersonById(partyId2);

                Framework framework = null;
                switch(frameworkComponent.toUpperCase()){
                case "TIME" : framework = new Time(frameworkReference, frameworkPrice, frameworkComponent);
                }

                ec_director.prepare();
                ec_director.buildExistentEcontract(econtractId, content, 
                        provider, consumer, 
                        framework, frameworkReference, frameworkPrice, 
                        microFraction, 
                        jitTimeToStart, 
                        enactmentValid, 
                        managementStatus);
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
            sql += "'" + ((Component)econtract.getFramework()).getReference() + "', ";
            sql += ((Component)econtract.getFramework()).getPrice() + ")";
//            System.out.println(sql);

            try {
                st.execute(sql);
                if(st.getUpdateCount() == 1){
                    System.err.println("..:INF:" + this.getClass().getSimpleName() + ":new Econtract was created! (" + econtract.getId() + ")");
                }
                else{
                    System.out.println("..:WRN:" + this.getClass().getSimpleName() + ":There have been inserted " + st.getUpdateCount() + " econtracts. Please contact sysadmin");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void setEcontractStatus(Econtract econtract, int status) throws Exception{
        long id = econtract.getId();
        int statusNow = econtract.getManagementEcontract().getStatus();
        
        
        try{
            Statement st;
            st = conexao.getConnection().createStatement();
            String sql = "select * from econtract where ID = " + id;
            String upd = "update ECONTRACT set MANAGEMENTSTATUS = " + status + " " 
                       + "where ID = " + id;
            ResultSet resultados = st.executeQuery(sql);

            if(resultados.getFetchSize() > 1)
                throw new Exception("..:ERR:More than one Econtract was found. Contact sysadmin!");
            
            /////////
            try {
                st = conexao.getConnection().createStatement();
                st.execute(upd);
                if(st.getUpdateCount() == 1){
                    System.err.println("..:INF:" + this.getClass().getSimpleName() + ":status of Econtract was updated! (" + econtract.getId() + ".status:" + status + ")");
                }
                else{
                    System.out.println("..:WRN:" + this.getClass().getSimpleName() + ":There have been updated " + st.getUpdateCount() + " econtracts. Please contact sysadmin");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            //////////
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
