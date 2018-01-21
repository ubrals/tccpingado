package resources.databases.dao.api;

import entities.Transaction;

public interface TransactionDaoInterface {
    void insertTransaction(Transaction transaction, long econtractId);
    long getLastTransactionSeq(long econtractId);

}
