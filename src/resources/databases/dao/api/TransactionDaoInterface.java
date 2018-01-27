package resources.databases.dao.api;

import entities.Transaction;

public interface TransactionDaoInterface {
    /**
     * 
     * @param transaction {@link Transaction}
     * @param econtractId long as Econtract id
     */
    void insertTransaction(Transaction transaction, long econtractId);
    /**
     * 
     * @param econtractId long as Econtract id
     * @return long as current transcation sequence
     */
    long getLastTransactionSeq(long econtractId);

}
