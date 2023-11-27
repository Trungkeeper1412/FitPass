package com.ks.fitpass.transaction.repository.Impl;

import com.ks.fitpass.transaction.dto.TransactionDTO;
import com.ks.fitpass.transaction.dto.TransferCreditHistory;
import com.ks.fitpass.transaction.repository.IRepositoryQuery;
import com.ks.fitpass.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TransactionRepositoryImpl implements TransactionRepository {

    private final JdbcTemplate jdbcTemplate;

    private static final Logger logger = LogManager.getLogger(TransactionRepositoryImpl.class);

    @Override
    public int insertTransaction(TransactionDTO transactionDTO) {
        return jdbcTemplate.update(IRepositoryQuery.INSERT_INTO_TRANSACTION,  transactionDTO.getWalletId(), transactionDTO.getAmount(),
                transactionDTO.getTransactionDate(), transactionDTO.getStatus());
    }

    @Override
    public List<TransactionDTO> getListTransactionByUserId(int userId) {
        return jdbcTemplate.query(IRepositoryQuery.GET_LIST_BY_USER_ID, (rs, rowNum) -> {
            TransactionDTO transactionDTO = new TransactionDTO();
            transactionDTO.setTransactionId(rs.getInt("transaction_id"));
            transactionDTO.setWalletId(rs.getInt("transaction_id"));
            transactionDTO.setAmount(rs.getInt("amount"));
            transactionDTO.setTransactionDate(rs.getTimestamp("transaction_date"));
            transactionDTO.setStatus(rs.getString("status"));
            return transactionDTO;
        }, userId);
    }

    @Override
    public int insertTransferCreditHistory(TransferCreditHistory transferCreditHistory) {
        return jdbcTemplate.update(IRepositoryQuery.INSERT_INTO_TRANSFER_CREDIT_HISTORY, transferCreditHistory.getSenderUserId(),
                transferCreditHistory.getReceiverUserId(), transferCreditHistory.getAmount(), transferCreditHistory.getOrderDetailId(),
                transferCreditHistory.getTransferDate());
    }

    @Override
    public Double getTotalAmountOfTransactionByUserId(int userId) {
        try {
            return jdbcTemplate.queryForObject(
                    IRepositoryQuery.GET_TOTAL_AMOUNT_TRANSACTION_BY_USER_ID,
                    new Object[]{userId},
                    new int[]{Types.INTEGER},
                    Double.class
            );

        } catch (DataAccessException e) {
            // Log the exception for further analysis
            logger.error("Error retrieving total amount for user ID {}: {}", userId, e.getMessage(), e);
            throw e; // Rethrow the exception if needed
        }
    }
}
