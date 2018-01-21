package resources.databases.impl.relacional;

import java.sql.ResultSet;
import java.sql.Statement;

import entities.CryptoPerson;
import entities.Party;
import entities.Transaction;
import entities.values.Content;
import pricing.Framework;
import pricing.components.Component;
import pricing.components.Time;
import resources.databases.dao.api.TransactionDaoInterface;

public class TransactionDaoReacional implements TransactionDaoInterface {
    private ConexaoInterface conexao;

    /**
     * @param conexao
     */
    public TransactionDaoReacional(ConexaoInterface conexao) {
        super();
        this.conexao = conexao;
    }

    @Override
    public void insertTransaction(Transaction transaction, long econtractId) {
        try{
            Statement st;
            st = conexao.getConnection().createStatement();
            String sql = "insert into TRANSACTIONS values (";
            sql += transaction.getId() + ", ";
            sql += econtractId + ", ";
            sql += "'" + transaction.getTrxtimestamp() + "', ";
            sql += transaction.getPrice() + ")";

            try {
                st.execute(sql);
                if(st.getUpdateCount() == 1){
                    System.err.println("..:INF:" + this.getClass().getSimpleName() + ":new Transaction:id(" + transaction.getId() + ") for econtract:" + econtractId + " was created!");
                }
                else{
                    System.out.println("..:WRN:" + this.getClass().getSimpleName() + ":There have been inserted " + st.getUpdateCount() + " transactions. Please contact sysadmin");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public long getLastTransactionSeq(long econtractId) {
        long id=0l;
        
        try{
            Statement st;
            st = conexao.getConnection().createStatement();
            String sql = "select max(ID) as id from transactions where ECONTRACTID = " + econtractId;
            ResultSet resultados = st.executeQuery(sql);

            while(resultados.next()){
                id = resultados.getLong("ID");
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        
        return id;
    }

}
