package com.ks.fitpass.department.service.impl;

import com.ks.fitpass.department.entity.DepositDenomination;
import com.ks.fitpass.department.repository.DepositDenominationRepository;
import com.ks.fitpass.department.service.DepositDenominationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DepositDenominationServiceImpl implements DepositDenominationService {
private final DepositDenominationRepository depositDenominationRepository;
    @Override
    public List<DepositDenomination> getAllDepositDenomination() {
      return  depositDenominationRepository.getAllDepositDenomination();
    }

    @Override
    public DepositDenomination getDepositDenominationById(int depositId) {
        return depositDenominationRepository.getDepositDenominationById(depositId);
    }

    @Override
    public int insertDepositDenomination(DepositDenomination depositDenomination) {
        return depositDenominationRepository.insertDepositDenomination(depositDenomination);
    }

    @Override
    public int updateDepositDenomination(DepositDenomination depositDenomination) {
        return depositDenominationRepository.updateDepositDenomination(depositDenomination);
    }

    @Override
    public int updateDepositDenominationStatus(int status, int id) {
        return depositDenominationRepository.updateDepositDenominationStatus(status,id);
    }
}
