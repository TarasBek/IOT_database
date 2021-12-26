package ua.lviv.iot.controller.impl;

import ua.lviv.iot.model.Transaction;
import ua.lviv.iot.service.impl.TransactionServiceImpl;

public class TransactionControllerImpl extends AbstractControllerImpl<Transaction, String> {
    public TransactionControllerImpl() {
        super(new TransactionServiceImpl());
    }
}
