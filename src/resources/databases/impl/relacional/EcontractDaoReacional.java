package resources.databases.impl.relacional;

import java.sql.ResultSet;
import java.sql.Statement;

import contracts.Econtract;
import contracts.EcontractDirector;
import entities.CryptoPerson;
import entities.Party;
import entities.values.Content;
import pricing.Framework;
import pricing.components.Component;
import pricing.components.Time;
import resources.databases.dao.api.EcontractDaoInterface;
import software.controllers.MasterController;

public class EcontractDaoReacional implements EcontractDaoInterface {
    private ConexaoInterface conexao;

    /**
     * 
     * @param conexao ConexaoInterface
     */
    public EcontractDaoReacional(ConexaoInterface conexao) {
        this.conexao = conexao;
    }

    /*
     * (non-Javadoc)
     * @see resources.databases.dao.api.EcontractDaoInterface#findEcontractById(long)
     */
    @Override
    public Econtract findEcontractById(long id) {
        EcontractDirector ec_director = new EcontractDirector();
        Econtract econtract = (Econtract) null;

//        ///
//        CtrlExchangedValue ctrl_exv;
//        ctrl_exv = new CtrlExchangedValue();
//        ////
//        CtrlCryptoPerson ctrl_crp;
//        ctrl_crp = new CtrlCryptoPerson();
//        ////
        MasterController controller = new MasterController();
        
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

                Content content = controller.findContentById(contentId);
                Party provider = controller.findCryptoPersonById(partyId1);
                Party consumer = controller.findCryptoPersonById(partyId2);

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

    /*
     * (non-Javadoc)
     * @see resources.databases.dao.api.EcontractDaoInterface#insertEcontract(contracts.Econtract)
     */
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
    
    /*
     * (non-Javadoc)
     * @see resources.databases.dao.api.EcontractDaoInterface#setEcontractStatus(contracts.Econtract, int)
     */
    @Override
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
