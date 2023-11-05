package com.ks.fitpass.transaction.service;

import com.ks.fitpass.transaction.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {
    int insertTransaction(TransactionDTO transactionDTO);
    List<TransactionDTO> getListTransactionByUserId(int userId);
}
