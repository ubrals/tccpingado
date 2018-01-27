package software.controllers;

import java.io.IOException;
import java.util.Collection;

import contracts.Econtract;
import contracts.EcontractDirector;
import contracts.Status;
import entities.CryptoPerson;
import entities.Party;
import entities.Transaction;
import entities.values.Content;
import pricing.Framework;
import resources.databases.dao.api.CryptoPersonDaoInterface;
import resources.databases.dao.api.EcontractDaoInterface;
import resources.databases.dao.api.ExchangedValueDaoInterface;
import resources.databases.dao.api.TransactionDaoInterface;
import resources.databases.impl.relacional.ConexaoInterface;
import resources.databases.impl.relacional.CryptoPersonDaoReacional;
import resources.databases.impl.relacional.EcontractDaoReacional;
import resources.databases.impl.relacional.ExchangedValueDaoReacional;
import resources.databases.impl.relacional.TransactionDaoReacional;
import resources.databases.impl.relacional.javadb.ConexaoDerbyDefault;

public class MasterController implements 
                                CryptoPersonDaoInterface, 
                                EcontractDaoInterface, 
                                ExchangedValueDaoInterface, 
                                TransactionDaoInterface {
    private ConexaoInterface conexao;
    
    /**
     * @param conexao {@link ConexaoInterface}
     */
    public MasterController() {
        super();
        this.conexao = ConexaoDerbyDefault.defaultConection();
    }
    /*
     * CryptoPerson methods
     * {begin}
     */
    /*
     * (non-Javadoc)
     * @see resources.databases.dao.api.CryptoPersonDaoInterface#findCryptoPersonById(long)
     */
    @Override
    public CryptoPerson findCryptoPersonById(long partyId){
        CryptoPersonDaoInterface crp_dao = new CryptoPersonDaoReacional(conexao);
        
        return crp_dao.findCryptoPersonById(partyId);
    }
    /*
     * CryptoPerson methods
     * {end}
     */
    
    /*
     * Econtract methods
     * {begin}
     */
    /*
     * (non-Javadoc)
     * @see resources.databases.dao.api.EcontractDaoInterface#findEcontractById(long)
     */
    @Override
    public Econtract findEcontractById(long id) {
        EcontractDaoInterface ect_dao = new EcontractDaoReacional(conexao);
        
        return ect_dao.findEcontractById(id);
    }
    
    /*
     * (non-Javadoc)
     * @see resources.databases.dao.api.EcontractDaoInterface#insertEcontract(contracts.Econtract)
     */
    @Override
    public void insertEcontract(Econtract econtract) {
        EcontractDaoInterface ect_dao = new EcontractDaoReacional(conexao);
        
        ect_dao.insertEcontract(econtract);
    }
    
    /**
     * 
     * @param content {@link Content}
     * @param provider {@link Party}
     * @param consumer {@link Party}
     * @param framework {@link Framework}
     * @param frameworkReference String as reference to calculate price
     * @param frameworkPrice double as price
     * @param fractionMicro int as time frame
     * @return {@link Econtract}
     * @throws Exception
     */
    public Econtract newEcontract(Content content, 
            Party provider, Party consumer, 
            Framework framework, String frameworkReference, double frameworkPrice, 
            int fractionMicro) throws Exception{
        EcontractDirector ec_director = new EcontractDirector();
        ec_director.prepare();
        ec_director.buildNewEcontract(content, provider, consumer, framework, frameworkReference, frameworkPrice, fractionMicro);
        Econtract econtract = ec_director.getObject();

        insertEcontract(econtract);

        return econtract;
    }
    
    /**
     * 
     * @param econtractId long as Econtract ID
     * @param content {@link Content}
     * @param provider {@link Party}
     * @param consumer {@link Party}
     * @param framework {@link Framework}
     * @param frameworkReference String as reference to calculate price
     * @param frameworkPrice double as price
     * @param fractionMicro int as time frame
     * @param jitTimeToStart long as timestamp setup by System.currentTimeMillis()
     * @param valid boolean as validity of Econtract
     * @param status int as status of Econtract
     * @return {@link Econtract}
     * @throws Exception
     */
    public Econtract existentEcontract(long econtractId, Content content, Party provider, Party consumer, Framework framework, String frameworkReference, double frameworkPrice, int fractionMicro, long jitTimeToStart, int valid, int status) throws Exception{
        EcontractDirector ec_director = new EcontractDirector();
        ec_director.prepare();
        ec_director.buildExistentEcontract(econtractId, content, provider, consumer, framework, frameworkReference, frameworkPrice, fractionMicro, jitTimeToStart, valid, status);
        Econtract econtract = ec_director.getObject();

        insertEcontract(econtract);

        return econtract;
    }
    
    /**
     * 
     * @param econtract {@link Econtract}
     * @param status {@link Status}
     * @throws Exception
     */
    public void setEcontractStatus(Econtract econtract, Status status) throws Exception{
        EcontractDirector director = new EcontractDirector();
        try {
            director.setEcontractStatus(econtract, status);
            int statusNew = econtract.getManagementEcontract().getStatus();
            EcontractDaoReacional ect_dao = new EcontractDaoReacional(ConexaoDerbyDefault.defaultConection());
            ect_dao.setEcontractStatus(econtract, statusNew);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
     * Econtract methods
     * {end}
     */
    
    /*
     * ExchangedValue methods
     * {begin}
     */
    /*
     * (non-Javadoc)
     * @see resources.databases.dao.api.ExchangedValueDaoInterface#listContents()
     */
    @Override
    public Collection<Content> listContents() {
        ExchangedValueDaoInterface exv_dao = new ExchangedValueDaoReacional(conexao);
        
        return exv_dao.listContents();
    }
    
    /*
     * (non-Javadoc)
     * @see resources.databases.dao.api.ExchangedValueDaoInterface#findContentById(long)
     */
    @Override
    public Content findContentById(long id) {
        ExchangedValueDaoInterface exv_dao = new ExchangedValueDaoReacional(conexao);
        return exv_dao.findContentById(id);
    }
    
    /**
     * 
     * @param contractId long as Econtract id
     * @param location String as directory where the file is located
     * @param filename String as name of the file
     * @return {@link String} as new symbolic link of the location for the Content to be delivered
     */
    public String makeSymLink(long contractId, String location, String filename){
        String file[] = filename.split("\\.");
        String extension = file[file.length-1];
        String old_file = location + "/" + filename;
        String new_file = location + "/" + contractId + "." + extension;
        String command[] = {
                "sudo",
                "/bin/ln",
                "-s",
                old_file, 
                new_file
        };
        try {
            Runtime.getRuntime().exec(command);
//            for(String cmd : command)
//                System.err.print(cmd + " ");
//            System.err.println("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new_file;
    }
    
    /**
     * 
     * @param server String as server's address
     * @param server_path String as the URL in server
     * @param contract_file String as content filename
     * @return {@link String} as the URL to be delivered
     */
    public String getURL(String server, String server_path, String contract_file){
        String uri[] = contract_file.split("/");
        String file = uri[uri.length-1];
        return server + server_path + file;
    }
    /*
     * ExchangedValue methods
     * {end}
     */
    
    /*
     * Transaction methods
     * {begin}
     */
    /*
     * (non-Javadoc)
     * @see resources.databases.dao.api.TransactionDaoInterface#insertTransaction(entities.Transaction, long)
     */
    @Override
    public void insertTransaction(Transaction transaction, long econtractId) {
        TransactionDaoInterface trx_dao = new TransactionDaoReacional(conexao);
        trx_dao.insertTransaction(transaction, econtractId);
    }
    
    /*
     * (non-Javadoc)
     * @see resources.databases.dao.api.TransactionDaoInterface#getLastTransactionSeq(long)
     */
    @Override
    public long getLastTransactionSeq(long econtractId) {
        TransactionDaoInterface trx_dao = new TransactionDaoReacional(conexao);
        return trx_dao.getLastTransactionSeq(econtractId);
    }
    /*
     * Transaction methods
     * {end}
     */
}
