package com.ks.fitpass.department.mapper;

import com.ks.fitpass.department.entity.DepositDenomination;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepositDenominationMapper implements RowMapper<DepositDenomination> {
    @Override
    public DepositDenomination mapRow(ResultSet resultSet, int i) throws SQLException {
        return DepositDenomination.builder()
                .depositDenominationId(resultSet.getInt("deposit_denomination_id"))
                .credit(resultSet.getInt("credit"))
                .money(resultSet.getInt("money"))
                .depositDenominationStatus(resultSet.getInt("deposit_denomination_status"))
                .build();
    }
}

