package com.ks.fitpass.core.mapper;

import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.core.entity.Wallet;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WalletMapper implements RowMapper<Wallet> {

    @Override
    public Wallet mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = User.builder()
                .userId(resultSet.getInt("user_id"))
                .build();

        return Wallet.builder()
                .walletId(resultSet.getInt("wallet_id"))
                .balance(resultSet.getDouble("balance"))
                .user(user)
                .build();
    }
}