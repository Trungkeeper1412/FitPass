package com.ks.fitpass.wallet.repository.impl;

import com.ks.fitpass.wallet.repository.IRepositoryQuery;
import com.ks.fitpass.wallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WalletRepositoryImpl implements WalletRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WalletRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public double getBalanceByUserId(int userId) {
        double balance = jdbcTemplate.queryForObject(IRepositoryQuery.GET_WALLET_BALANCE_BY_USER_ID, Double.class, userId);
        return balance;
    }

    @Override
    public int updateBalanceByUderId(int userId, double balance) {
        return jdbcTemplate.update(IRepositoryQuery.UPDATE_WALLET_BALANCE_BY_USER_ID, balance, userId);
    }
}