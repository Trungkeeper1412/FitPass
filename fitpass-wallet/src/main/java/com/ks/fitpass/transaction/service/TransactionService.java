package com.ks.fitpass.transaction.service;

import com.ks.fitpass.transaction.dto.TransactionDTO;
import com.ks.fitpass.transaction.dto.TransferCreditHistory;

import java.util.List;

public interface TransactionService {
    int insertTransaction(TransactionDTO transactionDTO);
    List<TransactionDTO> getListTransactionByUserId(int userId);

    int insertTransferCreditHistory(TransferCreditHistory transferCreditHistory);

    Double getTotalAmountOfTransactionByUserId(int userId);

    double countAllCredit();
}
