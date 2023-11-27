package com.ks.fitpass.transaction.service.Impl;

import com.ks.fitpass.transaction.dto.TransactionDTO;
import com.ks.fitpass.transaction.dto.TransferCreditHistory;
import com.ks.fitpass.transaction.repository.TransactionRepository;
import com.ks.fitpass.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public int insertTransaction(TransactionDTO transactionDTO) {
        return transactionRepository.insertTransaction(transactionDTO);
    }

    @Override
    public List<TransactionDTO> getListTransactionByUserId(int userId) {
        return transactionRepository.getListTransactionByUserId(userId);
    }

    @Override
    public int insertTransferCreditHistory(TransferCreditHistory transferCreditHistory) {
        return transactionRepository.insertTransferCreditHistory(transferCreditHistory);
    }
}
