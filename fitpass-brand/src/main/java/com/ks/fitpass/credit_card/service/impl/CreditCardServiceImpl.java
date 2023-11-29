package com.ks.fitpass.credit_card.service.impl;

import com.ks.fitpass.credit_card.dto.CreditCard;
import com.ks.fitpass.credit_card.repository.CreditCardRepository;
import com.ks.fitpass.credit_card.service.CreditCardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CreditCardServiceImpl implements CreditCardService {
    private final CreditCardRepository creditCardRepository;
    @Override
    public List<CreditCard> getAllByUserId(int userId) {
        return creditCardRepository.getAllByUserId(userId);
    }

    @Override
    public CreditCard getOne(int creditCardId) {
        return creditCardRepository.getOne(creditCardId);
    }

    @Override
    public int createCreditCard(CreditCard creditCard) {
        return creditCardRepository.createCreditCard(creditCard);
    }

    @Override
    public int updateCreditCard(CreditCard creditCard) {
        return creditCardRepository.updateCreditCard(creditCard);
    }

    @Override
    public int deleteCreditCard(int creditCardId) {
        return creditCardRepository.deleteCreditCard(creditCardId);
    }

    @Override
    public int getLastCreditCardId() {
        return creditCardRepository.getLastCreditCardId();
    }

    @Override
    public boolean checkCreditCardExist(CreditCard creditCard, int userId) {
        return creditCardRepository.checkCreditCardExist(creditCard, userId);
    }
}
