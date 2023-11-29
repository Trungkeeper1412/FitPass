package com.ks.fitpass.credit_card.repository.impl;

import com.ks.fitpass.credit_card.dto.CreditCard;
import com.ks.fitpass.credit_card.repository.CreditCardRepository;
import com.ks.fitpass.credit_card.repository.IRepositoryQuery;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class CreditCardRepositoryImpl implements CreditCardRepository, IRepositoryQuery {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<CreditCard> getAllByUserId(int userId) {
        return jdbcTemplate.query(GET_ALL_CREDIT_CARD, (resultSet, i) -> {
            CreditCard creditCard = new CreditCard();
            creditCard.setCreditCardId(resultSet.getInt("credit_card_id"));
            creditCard.setCardNumber(resultSet.getString("card_number"));
            creditCard.setUserId(resultSet.getInt("user_id"));
            creditCard.setCardOwnerName(resultSet.getString("card_owner_name"));
            creditCard.setStatus(resultSet.getString("status"));
            creditCard.setBankName(resultSet.getString("bank_name"));
            return creditCard;
        }, userId);
    }

    @Override
    public CreditCard getOne(int creditCardId) {
        return jdbcTemplate.queryForObject(GET_ONE_CREDIT_CARD, (resultSet, i) -> {
            CreditCard creditCard = new CreditCard();
            creditCard.setCreditCardId(resultSet.getInt("credit_card_id"));
            creditCard.setCardNumber(resultSet.getString("card_number"));
            creditCard.setUserId(resultSet.getInt("user_id"));
            creditCard.setCardOwnerName(resultSet.getString("card_owner_name"));
            creditCard.setStatus(resultSet.getString("status"));
            creditCard.setBankName(resultSet.getString("bank_name"));
            return creditCard;
        }, creditCardId);
    }

    @Override
    public int createCreditCard(CreditCard creditCard) {
        return jdbcTemplate.update(CREATE_CREDIT_CARD, creditCard.getUserId(), creditCard.getCardOwnerName(),
                creditCard.getCardNumber(), creditCard.getStatus(), creditCard.getBankName());
    }

    @Override
    public int updateCreditCard(CreditCard creditCard) {
        return jdbcTemplate.update(UPDATE_CREDIT_CARD, creditCard.getCardOwnerName(),
                creditCard.getCardNumber(), creditCard.getStatus(), creditCard.getBankName(),creditCard.getCreditCardId());
    }

    @Override
    public int deleteCreditCard(int creditCardId) {
        return jdbcTemplate.update(DELETE_CREDIT_CARD, creditCardId);
    }

    @Override
    public int getLastCreditCardId() {
        return jdbcTemplate.queryForObject(GET_LAST_CREDIT_CARD_ID, Integer.class);
    }

    @Override
    public boolean checkCreditCardExist(CreditCard creditCard, int userId) {
        int count = jdbcTemplate.queryForObject(CHECK_CREDIT_CARD_EXIST, Integer.class, creditCard.getCardNumber(), creditCard.getBankName(), userId);
        return count > 0;
    }
}
