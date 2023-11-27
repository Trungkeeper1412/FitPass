package com.ks.fitpass.transaction.repository;

import com.ks.fitpass.transaction.dto.TransactionDTO;
import com.ks.fitpass.transaction.dto.TransferCreditHistory;

import java.util.List;

public interface TransactionRepository {
    int insertTransaction(TransactionDTO transactionDTO);
    List<TransactionDTO> getListTransactionByUserId(int userId);

    int insertTransferCreditHistory(TransferCreditHistory transferCreditHistory);
}
