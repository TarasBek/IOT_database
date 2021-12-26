package ua.lviv.iot.service.impl;

import ua.lviv.iot.dao.impl.TransactionDaoImpl;
import ua.lviv.iot.model.Transaction;

public class TransactionServiceImpl extends AbstractServiceImpl<Transaction, String> {
    public TransactionServiceImpl() {
        super(new TransactionDaoImpl());
    }
}
