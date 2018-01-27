package software.controllers;

import entities.Transaction;
import resources.databases.dao.api.TransactionDaoInterface;
import resources.databases.impl.relacional.TransactionDaoReacional;
import resources.databases.impl.relacional.javadb.ConexaoDerbyDefault;

public class _deprec_CtrlTransaction implements TransactionDaoInterface {

    @Override
    public void insertTransaction(Transaction transaction, long econtractId) {
        TransactionDaoInterface trx_dao = new TransactionDaoReacional(ConexaoDerbyDefault.defaultConection());
        trx_dao.insertTransaction(transaction, econtractId);
    }

    @Override
    public long getLastTransactionSeq(long econtractId) {
        TransactionDaoInterface trx_dao = new TransactionDaoReacional(ConexaoDerbyDefault.defaultConection());
        return trx_dao.getLastTransactionSeq(econtractId);
    }

}
