package com.ks.fitpass.credit_card.service;

import com.ks.fitpass.credit_card.dto.CreditCard;

import java.util.List;

public interface CreditCardService {
    List<CreditCard> getAllByUserId(int userId);
    CreditCard getOne(int creditCardId);
    int createCreditCard(CreditCard creditCard);
    int updateCreditCard(CreditCard creditCard);
    int deleteCreditCard(int creditCardId);
    int getLastCreditCardId();
    boolean checkCreditCardExist(CreditCard creditCard, int userId);
}
