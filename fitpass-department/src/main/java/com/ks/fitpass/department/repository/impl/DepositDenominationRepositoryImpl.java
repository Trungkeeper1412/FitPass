package com.ks.fitpass.department.repository.impl;

import com.ks.fitpass.department.entity.DepositDenomination;
import com.ks.fitpass.department.repository.DepositDenominationRepository;
import com.ks.fitpass.department.repository.IRepositoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class DepositDenominationRepositoryImpl implements DepositDenominationRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public DepositDenominationRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<DepositDenomination> getAllDepositDenomination() {
        return jdbcTemplate.query(GET_ALL_DEPOSIT_DENOMINATION, (rs, rowNum) -> {
            DepositDenomination depositDenomination = new DepositDenomination();
            depositDenomination.setDepositDenominationId(rs.getInt("deposit_denomination_id"));
            depositDenomination.setCredit(rs.getInt("credit"));
            depositDenomination.setMoney(rs.getInt("money"));
            depositDenomination.setDepositDenominationStatus(rs.getInt("deposit_denomination_status"));
            return depositDenomination;
        });
    }
}
