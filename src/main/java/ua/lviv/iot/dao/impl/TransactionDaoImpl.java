package ua.lviv.iot.dao.impl;

import ua.lviv.iot.model.Transaction;

public class TransactionDaoImpl extends AbstractDaoImpl<Transaction, String> {
    public TransactionDaoImpl() {
        super(Transaction.class);
    }
}
