package com.ks.fitpass.department.repository;

import com.ks.fitpass.department.entity.DepositDenomination;

import java.util.List;

public interface DepositDenominationRepository {
List<DepositDenomination> getAllDepositDenomination();

DepositDenomination getDepositDenominationById(int depositId);

int insertDepositDenomination (DepositDenomination depositDenomination);

int updateDepositDenomination (DepositDenomination depositDenomination);

int updateDepositDenominationStatus (int status, int id);

List<DepositDenomination> getAllDepositDenominationActive();


}
